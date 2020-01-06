package com.romeu.nutricao.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.romeu.nutricao.models.Alimento;
import com.romeu.nutricao.models.dto.MacrosDTO;
import com.romeu.nutricao.models.dto.RefeicaoDTO;
import com.romeu.nutricao.models.dto.TabelaIdDescricao;
import com.romeu.nutricao.repository.AlimentoRepository;

@RestController
public class AlimentoController {

	@Autowired
	private AlimentoRepository alimentoRepo;
	
	private ResponseEntity response;
	
	@GetMapping("/{id}")
	public ResponseEntity<Alimento> getAlimento(@PathVariable int id) {
		
		alimentoRepo.findById(id).ifPresentOrElse(
				value -> { response = new ResponseEntity<Alimento>(value, HttpStatus.OK);}, 
				() -> { response = new ResponseEntity<Alimento>(HttpStatus.NOT_FOUND);});
		
		return response;
	}
	
	@GetMapping("/macros/{id}/{porcao}")
	public ResponseEntity<MacrosDTO> getMacros(@PathVariable int id, @PathVariable int porcao) {
		
		if(porcao < 0) return new ResponseEntity<MacrosDTO>(HttpStatus.BAD_REQUEST);
	
		alimentoRepo.findById(id).ifPresentOrElse(
				value -> { response = new ResponseEntity<MacrosDTO>(calculateMacros(value, porcao), HttpStatus.OK);}, 
				() -> { response = new ResponseEntity<MacrosDTO>(HttpStatus.NOT_FOUND);});
		
		return response;
	}
	
	public MacrosDTO calculateMacros(Alimento alimento, int porcao) {
		
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
	
	@GetMapping("/macros/refeicao")
	public ResponseEntity<MacrosDTO> getMacrosRefeicao(@RequestBody List<RefeicaoDTO> refeicoes) {
		
		MacrosDTO macros = new MacrosDTO();
		macros.setDescricao("Macros da Refeição");
		macros.setCarboidrato(0);
		macros.setFibra_Alimentar(0);
		macros.setKcal(0);
		macros.setKj(0);
		macros.setLipideos(0);
		macros.setProteina(0);
		
		
		for (RefeicaoDTO qtd : refeicoes) {

			Optional<Alimento> op = alimentoRepo.findById(qtd.getId());
			
			if(op.isPresent()) {
				macros = calculateMacrosRefeicao(op.get(), qtd, macros);
				
			} else {
				return new ResponseEntity<MacrosDTO>(HttpStatus.BAD_REQUEST);
			}
		}
	
		
		return new ResponseEntity<MacrosDTO>(macros, HttpStatus.OK);
	}
	
	public MacrosDTO calculateMacrosRefeicao(Alimento alimento, RefeicaoDTO qtd, MacrosDTO macros) {
		
		MacrosDTO macrosAlimento = calculateMacros(alimento, qtd.getPorcao());
		
		macros.setCarboidrato(macros.getCarboidrato() + macrosAlimento.getCarboidrato());
		macros.setFibra_Alimentar(macros.getFibra_Alimentar() + macrosAlimento.getFibra_Alimentar());
		macros.setKcal(macros.getKcal() + macrosAlimento.getKcal());
		macros.setKj(macros.getKj() + macrosAlimento.getKj());
		macros.setLipideos(macros.getLipideos() + macrosAlimento.getLipideos());
		macros.setProteina(macros.getProteina() + macrosAlimento.getProteina());
		
		
		return macros;
	}
	
	@GetMapping("/alimentos")
	public ResponseEntity<List<TabelaIdDescricao>> getAlimentos() {
		
		List<TabelaIdDescricao> a = alimentoRepo.getIdAndDescricao();
		
		return new ResponseEntity<List<TabelaIdDescricao>>(a, HttpStatus.OK);
	}
	
	@PostMapping("/")
	public ResponseEntity<Alimento> postAlimento() {
		//necessario criar dto e validação dos campos
		return null;
	}
	
	
}
