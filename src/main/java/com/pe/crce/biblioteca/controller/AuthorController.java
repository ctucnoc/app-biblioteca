package com.pe.crce.biblioteca.controller;

import javax.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.pe.crce.biblioteca.constant.BibliotecaConstant;
import com.pe.crce.biblioteca.dto.AuthorDTO;
import com.pe.crce.biblioteca.dto.HrefEntityDTO;
import com.pe.crce.biblioteca.dto.PageableDTO;
import com.pe.crce.biblioteca.dto.request.AuthorDTORequest;
import com.pe.crce.biblioteca.service.AuthorService;
import com.pe.crce.biblioteca.util.BibliotecaUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(BibliotecaConstant.RESOURCE_GENERIC)
public class AuthorController {

	final 
	AuthorService authorService;
	
	final
	BibliotecaUtil util;

	public AuthorController(AuthorService authorService, BibliotecaUtil util) {
		this.authorService = authorService;
		this.util = util;
	}

	@GetMapping(BibliotecaConstant.RESOURCE_AUTHORS + BibliotecaConstant.RESOURCE_AUTHORS_AUTHOR)
	public ResponseEntity<Page<AuthorDTO>> findByKeyWordSQL(@RequestParam String key_word,PageableDTO pageable) {
		return new ResponseEntity<Page<AuthorDTO>>(this.authorService.findByKeyWordSQL(key_word,this.util.getPageable(pageable)), HttpStatus.OK);
	}
	
	@PostMapping(BibliotecaConstant.RESOURCE_AUTHORS + BibliotecaConstant.RESOURCE_AUTHORS_AUTHOR)
	public ResponseEntity<HrefEntityDTO> saveSQL(@Valid @RequestBody AuthorDTORequest dto) {
		log.info("crce controller saveSQL -> {} "+dto.toString());
		return new ResponseEntity<HrefEntityDTO>(this.authorService.saveSQL(dto), HttpStatus.CREATED);
	}
	
	@GetMapping(BibliotecaConstant.RESOURCE_AUTHORS + BibliotecaConstant.RESOURCE_AUTHORS_AUTHOR
			+ BibliotecaConstant.RESOURCE_GENERIC_ID)
	public ResponseEntity<AuthorDTO> findById(@PathVariable Long id) {
		return new ResponseEntity<AuthorDTO>(this.authorService.findById(id), HttpStatus.OK);
	}

	@PutMapping(BibliotecaConstant.RESOURCE_AUTHORS + BibliotecaConstant.RESOURCE_AUTHORS_AUTHOR
			+ BibliotecaConstant.RESOURCE_GENERIC_ID)
	public ResponseEntity<HrefEntityDTO> update(@RequestBody @Valid AuthorDTORequest dto, @PathVariable Long id) {
		log.info("crce controller -> {} "+dto.toString());
		return new ResponseEntity<HrefEntityDTO>(this.authorService.update(dto, id), HttpStatus.OK);
	}

	@DeleteMapping(BibliotecaConstant.RESOURCE_AUTHORS + BibliotecaConstant.RESOURCE_AUTHORS_AUTHOR
			+ BibliotecaConstant.RESOURCE_GENERIC_ID)
	public ResponseEntity<HrefEntityDTO> delete(@PathVariable Long id) {
		return new ResponseEntity<HrefEntityDTO>(this.authorService.delete(id), HttpStatus.OK);
	}

}
