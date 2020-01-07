package com.romeu.nutricao.service;

import java.util.List;

import com.romeu.nutricao.exception.BadRequestException;
import com.romeu.nutricao.exception.NotFoundException;
import com.romeu.nutricao.models.dto.MacrosDTO;
import com.romeu.nutricao.models.dto.PorcaoDTO;

public interface MacrosService {
	
	public MacrosDTO calculateRefeicao(List<PorcaoDTO> qtd) throws NotFoundException;
	
	public MacrosDTO calculateMacros(int id, int porcao) throws NotFoundException, BadRequestException;
	
}
