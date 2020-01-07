package com.romeu.nutricao.service.impl;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.romeu.nutricao.exception.NotFoundException;
import com.romeu.nutricao.models.Alimento;
import com.romeu.nutricao.models.dto.AlimentoDTO;
import com.romeu.nutricao.projections.TabelaIdDescricao;
import com.romeu.nutricao.repository.AlimentoRepository;
import com.romeu.nutricao.service.AlimentoService;

@Service
public class AlimentoServiceImpl implements AlimentoService {
	
	@Autowired
	private AlimentoRepository alimentoRepo;

	@Override
	public Alimento createAlimento(AlimentoDTO alimentoDTO)  {

		ModelMapper modelMapper = new ModelMapper();
		Alimento alimento = modelMapper.map(alimentoDTO, Alimento.class);
		
		return alimentoRepo.save(alimento);
	}

	@Override
	public Alimento updateAlimento(AlimentoDTO alimentoDTO, int id) throws NotFoundException {

		Optional<Alimento> op = alimentoRepo.findById(id);
		
		if( op.isPresent() ) {
			return update(alimentoDTO, op.get() );
		} else {
			throw new NotFoundException("Elemento não encontrado");
		}
	}

	@Override
	public void deleteAlimento(int id) throws NotFoundException {
		
		alimentoRepo.deleteById(id);
	}

	@Override
	public Alimento readAlimento(int id) throws NotFoundException {
		
		return alimentoRepo.findById(id).get();
	}

	@Override
	public List<TabelaIdDescricao> readAlimentos() {

		return alimentoRepo.getIdAndDescricao();
	}
	
	public Alimento update(AlimentoDTO alimentoDTO, Alimento alimento) {
		
		ModelMapper modelMapper = new ModelMapper();
		Alimento novo = modelMapper.map(alimentoDTO, Alimento.class);
		novo.setId(alimento.getId());
		alimentoRepo.save(novo);
		return novo;
	}

}
