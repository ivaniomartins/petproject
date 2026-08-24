package com.petproject.martins;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.Transactional;

import com.petproject.martins.model.Atendimento;
import com.petproject.martins.model.ItemAtendimento;
import com.petproject.martins.model.Paciente;
import com.petproject.martins.model.Procedimento;
import com.petproject.martins.model.Tutor;
import com.petproject.martins.model.enuns.Especie;
import com.petproject.martins.repositories.AtendimentoRepository;
import com.petproject.martins.repositories.ItemAtendimentoRepository;
import com.petproject.martins.repositories.PacienteRepository;
import com.petproject.martins.repositories.ProcedimentoRepository;
import com.petproject.martins.repositories.TutorRepository;

@SpringBootApplication
public class PetprojectApplication {

	public static void main(String[] args) {
		SpringApplication.run(PetprojectApplication.class, args);
	}

	/**
	 * Seed de dados — só roda no perfil de desenvolvimento.
	 * Em produção este bean não é registrado, evitando popular o banco com dados
	 * fake a cada boot.
	 */
	@Profile("dev")
	@org.springframework.stereotype.Component
	static class DevSeed implements CommandLineRunner {

		private final TutorRepository tutorRepository;
		private final PacienteRepository pacienteRepository;
		private final AtendimentoRepository atendimentoRepository;
		private final ProcedimentoRepository procedimentoRepository;
		private final ItemAtendimentoRepository itemRepository;

		DevSeed(TutorRepository tutorRepository, PacienteRepository pacienteRepository,
				AtendimentoRepository atendimentoRepository, ProcedimentoRepository procedimentoRepository,
				ItemAtendimentoRepository itemRepository) {
			this.tutorRepository = tutorRepository;
			this.pacienteRepository = pacienteRepository;
			this.atendimentoRepository = atendimentoRepository;
			this.procedimentoRepository = procedimentoRepository;
			this.itemRepository = itemRepository;
		}

		@Override
		@Transactional
		public void run(String... args) throws Exception {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

			Tutor t1 = new Tutor(null, "Ivanio Martins", "092.348.134-64", "ivaniomartins1991@gmail.com", null);
			Tutor t2 = new Tutor(null, "Janinny Pessoa", "092.348.135-65", "janinny_pessoa@hotmail.com", null);

			Paciente p1 = new Paciente(null, "Nakia", "American Bully", 5.4, sdf.parse("14/04/2023"),
					Especie.CANINA, t1);
			Paciente p2 = new Paciente(null, "Luna", "Pit-Bull", 30.0, sdf.parse("20/02/2014"), Especie.CANINA, t1);
			Paciente p3 = new Paciente(null, "Nina", "Doushouse", 30.0, sdf.parse("20/12/2019"), Especie.CANINA, t2);

			Atendimento a1 = new Atendimento(null, sdf.parse("24/06/2023"), p1);

			Procedimento pc1 = new Procedimento(null, "Consulta", 80.00);
			Procedimento pc2 = new Procedimento(null, "Vacina", 100.00);

			ItemAtendimento i1 = new ItemAtendimento(null, pc1, a1);
			ItemAtendimento i2 = new ItemAtendimento(null, pc2, a1);

			tutorRepository.saveAll(Arrays.asList(t1, t2));
			pacienteRepository.saveAll(Arrays.asList(p1, p2, p3));
			atendimentoRepository.saveAll(Arrays.asList(a1));
			procedimentoRepository.saveAll(Arrays.asList(pc1, pc2));
			itemRepository.saveAll(Arrays.asList(i1, i2));
		}
	}
}