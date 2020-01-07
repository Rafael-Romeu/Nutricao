package com.romeu.nutricao.service;

import java.util.List;

import com.romeu.nutricao.exception.NotFoundException;
import com.romeu.nutricao.models.Alimento;
import com.romeu.nutricao.models.dto.AlimentoDTO;
import com.romeu.nutricao.projections.TabelaIdDescricao;

public interface AlimentoService {
	
	public Alimento createAlimento(AlimentoDTO alimentoDTO);
	
	public Alimento updateAlimento(AlimentoDTO alimentoDTO, int id) throws NotFoundException;
	
	public void deleteAlimento(int id) throws NotFoundException;
	
	public Alimento readAlimento(int id) throws NotFoundException;
	
	public List<TabelaIdDescricao> readAlimentos();
	
}
