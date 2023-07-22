package com.petproject.martins;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
public class PetprojectApplication implements CommandLineRunner {

	@Autowired
	private TutorRepository tutorRepository;

	@Autowired
	private PacienteRepository pacienteRepository;

	@Autowired
	private AtendimentoRepository atendimentoRepository;

	@Autowired
	private ProcedimentoRepository procedimentoRepository;

	@Autowired
	private ItemAtendimentoRepository itemRepository;

	public static void main(String[] args) {
		SpringApplication.run(PetprojectApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {

		Tutor t1 = new Tutor(null, "Ivanio Martins", "092.348.134-64", null);
		Tutor t2 = new Tutor(null, "Janinny Pessoa", "092.348.135-65", null);

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		Paciente p1 = new Paciente(null, "Nakia", "American Bully", 5.4, sdf.parse("14/04/2023"), Especie.CANINA, t1);
		Paciente p2 = new Paciente(null, "Luna", "Pit-Bull", 30.0, sdf.parse("20/02/2014"), Especie.CANINA, t1);
		Paciente p3 = new Paciente(null, "Nina", "Doushouse", 30.0, sdf.parse("20/12/2019"), Especie.CANINA, t2);

		Atendimento a1 = new Atendimento(null, sdf.parse("24/06/2023"), p1);

		Procedimento pc1 = new Procedimento(null, "Consulta", 80.00);
		Procedimento pc2 = new Procedimento(null, "Vacina", 100.00);
		
		ItemAtendimento i1 = new ItemAtendimento(null, pc1,a1);
		ItemAtendimento i2 = new ItemAtendimento(null, pc2,a1);

		tutorRepository.saveAll(Arrays.asList(t1, t2));
		pacienteRepository.saveAll(Arrays.asList(p1, p2, p3));
		atendimentoRepository.saveAll(Arrays.asList(a1));
		procedimentoRepository.saveAll(Arrays.asList(pc1,pc2));
		itemRepository.saveAll(Arrays.asList(i1,i2));

	}

}
