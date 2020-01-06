package com.romeu.nutricao.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "alimentos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Alimento {

	@Id private int id;
	private String Descricao;
	private double Umidade;
	private double kcal;
	private double kj;
	private double Proteina;
	private double Lipideos;
	private String Colesterol;
	private double Carboidrato;
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
  	private int porcao;

}
