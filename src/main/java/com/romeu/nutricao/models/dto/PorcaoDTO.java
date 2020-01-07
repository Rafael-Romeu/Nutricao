package com.romeu.nutricao.models.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class PorcaoDTO {

	@NotNull @Min(0) private int id;
	@NotNull @Min(0) private int porcao;
}
