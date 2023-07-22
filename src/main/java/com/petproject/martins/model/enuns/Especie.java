package com.petproject.martins.model.enuns;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Especie {
	
	CANINA ("CANINA", 1),
	FELINA ("FELINA", 2);

   
	private String cod;
	private int tipo;
	

	
	public static Especie toEnum(String cod) {
		if (cod==null) {
        return null;
	}
		
		for(Especie x: Especie.values()) {
			
		   if (cod.equals(x.getCod())){ 
			    return x;
		   }
	    }
		throw new IllegalArgumentException("Id inválido " + cod );
	}
	
	

}
