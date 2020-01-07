package com.romeu.nutricao.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.romeu.nutricao.exception.BadRequestException;
import com.romeu.nutricao.exception.NotFoundException;
import com.romeu.nutricao.models.Alimento;
import com.romeu.nutricao.models.dto.MacrosDTO;
import com.romeu.nutricao.models.dto.PorcaoDTO;
import com.romeu.nutricao.repository.AlimentoRepository;
import com.romeu.nutricao.service.MacrosService;

@Service
public class MacrosServiceImpl implements MacrosService {
	
	@Autowired
	private AlimentoRepository alimentoRepo;

	@Override
	public MacrosDTO calculateRefeicao(List<PorcaoDTO> qtd) throws NotFoundException {
		
		MacrosDTO macros = new MacrosDTO("Macros da Refeição", 0, 0, 0, 0, 0, 0);
		
		for (PorcaoDTO porcao : qtd) {
			
			Optional<Alimento> op = alimentoRepo.findById(porcao.getId());
			
			if (op.isPresent() ) {
				
				MacrosDTO macrosAlimento = calculate(op.get(), porcao.getPorcao());

				macros.setCarboidrato(macros.getCarboidrato() + macrosAlimento.getCarboidrato());
				macros.setFibra_Alimentar(macros.getFibra_Alimentar() + macrosAlimento.getFibra_Alimentar());
				macros.setKcal(macros.getKcal() + macrosAlimento.getKcal());
				macros.setKj(macros.getKj() + macrosAlimento.getKj());
				macros.setLipideos(macros.getLipideos() + macrosAlimento.getLipideos());
				macros.setProteina(macros.getProteina() + macrosAlimento.getProteina());
				
			} else {
				
				throw new NotFoundException("Alimento não encontrado");
			}
			
		}
		
		return macros;
	}

	@Override
	public MacrosDTO calculateMacros(int id, int porcao) throws BadRequestException, NotFoundException {
		
		if ( porcao < 0 ) throw new BadRequestException("Porção deve ser maior que 0");
		
		Optional<Alimento> op = alimentoRepo.findById(id);
		
		if (op.isPresent() ) {
			
			return calculate(op.get(), porcao);
			
		} else {
			
			throw new NotFoundException("Alimento não encontrado");
		}

	}
	
	public MacrosDTO calculate(Alimento alimento, int porcao) {
		
		MacrosDTO macros = new MacrosDTO();
		
		double x = porcao / alimento.getPorcao();
		
		macros.setDescricao(alimento.getDescricao());
		macros.setCarboidrato(x * alimento.getCarboidrato());
		macros.setFibra_Alimentar(x * alimento.getFibra_Alimentar());
		macros.setKcal(x * alimento.getKcal());
		macros.setKj(x * alimento.getKj());
		macros.setLipideos(x * alimento.getLipideos());
		macros.setProteina(x * alimento.getProteina());
		
		return macros;
		
	}
}
