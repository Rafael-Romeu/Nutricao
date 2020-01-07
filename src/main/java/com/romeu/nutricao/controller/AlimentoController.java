package com.romeu.nutricao.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.romeu.nutricao.exception.ExceptionStatus;
import com.romeu.nutricao.models.Alimento;
import com.romeu.nutricao.models.dto.AlimentoDTO;
import com.romeu.nutricao.projections.TabelaIdDescricao;
import com.romeu.nutricao.service.AlimentoService;

@RestController
@RequestMapping("/alimento")
public class AlimentoController {

	@Autowired
	private AlimentoService alimentoService;
	
	@GetMapping()
	public ResponseEntity<List<TabelaIdDescricao>> getAlimentos() {
		
		try {
			return ResponseEntity.status(HttpStatus.OK).body(alimentoService.readAlimentos());
	    } catch (Exception exc) {
	         throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, exc.getMessage(), exc);
	    }
	}

	@GetMapping("/{id}")
	public ResponseEntity<Alimento> getAlimento(@PathVariable int id) {
		
		try {
			return ResponseEntity.status(HttpStatus.OK).body(alimentoService.readAlimento(id));
	    } catch (Exception exc) {
	         throw new ResponseStatusException(((ExceptionStatus) exc).getStatus(), exc.getMessage(), exc);
	    }
	}
	
	
	@PostMapping("/")
	public ResponseEntity<Alimento> postAlimento(@Valid @RequestBody AlimentoDTO alimentoDTO) {
		
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(alimentoService.createAlimento(alimentoDTO));
	    } catch (Exception exc) {
	         throw new ResponseStatusException(((ExceptionStatus) exc).getStatus(), exc.getMessage(), exc);
	    }
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Alimento> deleteAlimento(@PathVariable int id) {
		
		try {
			alimentoService.deleteAlimento(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	    } catch (Exception exc) {
	         throw new ResponseStatusException(((ExceptionStatus) exc).getStatus(), exc.getMessage(), exc);
	    }
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Alimento> putAlimento(@Valid @RequestBody AlimentoDTO alimentoDTO, @PathVariable int id) {
		
		try {
			return ResponseEntity.status(HttpStatus.OK).body(alimentoService.updateAlimento(alimentoDTO, id));
	    } catch (Exception exc) {
	         throw new ResponseStatusException(((ExceptionStatus) exc).getStatus(), exc.getMessage(), exc);
	    }
	}
	
	
	
	
}
