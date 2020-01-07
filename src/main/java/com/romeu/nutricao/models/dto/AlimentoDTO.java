package com.romeu.nutricao.models.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class AlimentoDTO {
	
	@NotBlank private String Descricao;
	private double Umidade;
	@NotNull @Min(0) private double kcal;
	@NotNull @Min(0) private double kj;
	@NotNull @Min(0) private double Proteina;
	@NotNull @Min(0) private double Lipideos;
	private String Colesterol;
	@NotNull @Min(0) private double Carboidrato;
	private double Fibra_Alimentar;
	private double Cinzas;
	private String Magnesio;
	private String Manganes;
	private String Fosforo;
	private String Ferro;
	private String Sodio;
	private String Potassio;
	private String Cobre;
	private String Zinco;
	private String Retinol;
	private String RE;
	private String RAE;
  	private String Tiamina;
  	private String Riboflavina;
  	private String Piridoxina;
  	private String Niacina;
  	private String vitamina_C;
  	@NotNull @Min(0) private int porcao;

}
