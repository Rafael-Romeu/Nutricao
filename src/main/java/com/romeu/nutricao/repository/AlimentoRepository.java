package com.romeu.nutricao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.romeu.nutricao.models.Alimento;
import com.romeu.nutricao.models.dto.TabelaIdDescricao;

public interface AlimentoRepository extends CrudRepository<Alimento, Integer> {
	
	@Query(value = "select * from alimentos", nativeQuery = true)
	public List<TabelaIdDescricao> getIdAndDescricao();

}
