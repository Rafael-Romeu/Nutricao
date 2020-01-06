package com.romeu.nutricao.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MacrosDTO {
	
	private String Descricao;
	private double kcal;
	private double kj;
	private double Proteina;
	private double Lipideos;
	private double Carboidrato;
	private double Fibra_Alimentar;

}
