package com.generation.farmacia.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.generation.farmacia.model.Categoria;
import com.generation.farmacia.repository.CategoriaRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/categoria")
@CrossOrigin(origins = "*" ,allowedHeaders = "*" )
public class CategoriaController {

	@Autowired
	private CategoriaRepository categoriaRepository ;
	
	@GetMapping("/all")
	public ResponseEntity<List<Categoria>> getAll(){
		return ResponseEntity.ok(categoriaRepository.findAll()); // select * from tb_postagem;
		
		}
	
	@GetMapping("/{id}")
	public ResponseEntity <Categoria> getById(@PathVariable Long id){
		return categoriaRepository.findById(id)
				.map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	@GetMapping("/nome/{nome}")
	public ResponseEntity <List<Categoria>> getByNome(@PathVariable String nome){
		return ResponseEntity.ok(categoriaRepository.findAllByNomeContainingIgnoreCase(nome));
		}
	
	@PostMapping
	public ResponseEntity<Categoria> post (@Valid @RequestBody Categoria categoria){
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(categoriaRepository.save(categoria));
		// insert into tb_postagem (titulo,texto)values (valores...);
	}
	
	@PutMapping("/atualizar")
	public ResponseEntity<Categoria> put (@Valid @RequestBody Categoria categoria){
		return categoriaRepository.findById(categoria.getId())
				.map(resposta -> ResponseEntity.status(HttpStatus.CREATED)
				.body(categoriaRepository.save(categoria)))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
		// uptade into tb_postagem  set titulo = ?, texto = ?  where id = ? ;
		
	}
	
	@DeleteMapping ("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete( @PathVariable long id) {
		
		Optional<Categoria> categoria = categoriaRepository.findById(id);

		if (categoria.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		
		categoriaRepository.deleteById(id);
	}
}
