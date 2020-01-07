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
	private String descricao;
	private double umidade;
	private double kcal;
	private double kj;
	private double proteina;
	private double lipideos;
	private String colesterol;
	private double carboidrato;
	private double fibra_Alimentar;
	private double cinzas;
	private String magnesio;
	private String manganes;
	private String fosforo;
	private String ferro;
	private String sodio;
	private String potassio;
	private String cobre;
	private String zinco;
	private String retinol;
	private String rE;
	private String rAE;
  	private String tiamina;
  	private String riboflavina;
  	private String piridoxina;
  	private String niacina;
  	private String vitamina_C;
  	private int porcao;

}
