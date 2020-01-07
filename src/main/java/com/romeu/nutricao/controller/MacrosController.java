package com.romeu.nutricao.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.romeu.nutricao.exception.ExceptionStatus;
import com.romeu.nutricao.exception.NotFoundException;
import com.romeu.nutricao.models.dto.MacrosDTO;
import com.romeu.nutricao.models.dto.PorcaoDTO;
import com.romeu.nutricao.service.MacrosService;

@RestController
@RequestMapping("/macros")
public class MacrosController {
	
	@Autowired
	private MacrosService macrosService;
	
	@GetMapping("/{id}/{porcao}")
	public ResponseEntity<MacrosDTO> getMacros(@PathVariable int id, @PathVariable int porcao) {
				
		try {
			return ResponseEntity.status(HttpStatus.OK).body(macrosService.calculateMacros(id, porcao));
	    } catch (Exception exc) {
	         throw new ResponseStatusException(((ExceptionStatus) exc).getStatus(), exc.getMessage(), exc);
	    }
	}
	
	@GetMapping("/refeicao")
	public ResponseEntity<MacrosDTO> getMacrosRefeicao(@Valid @RequestBody List<PorcaoDTO> porcoes) {
		
		try {
			return ResponseEntity.status(HttpStatus.OK).body(macrosService.calculateRefeicao(porcoes));
		} catch (NotFoundException exc) {
			throw new ResponseStatusException(exc.getStatus(), exc.getMessage(), exc);
		}
	}
}
