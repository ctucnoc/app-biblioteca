package com.pe.crce.biblioteca.controller;

import java.util.List;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.pe.crce.biblioteca.constant.BibliotecaConstant;
import com.pe.crce.biblioteca.dto.AuthorDTO;
import com.pe.crce.biblioteca.dto.HrefEntityDTO;
import com.pe.crce.biblioteca.dto.request.AuthorDTORequest;
import com.pe.crce.biblioteca.service.AuthorService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(BibliotecaConstant.RESOURCE_GENERIC)
public class AuthorController {

	final 
	AuthorService authorService;

	public AuthorController(AuthorService authorService) {
		this.authorService = authorService;
	}

	@GetMapping(BibliotecaConstant.RESOURCE_AUTHORS + BibliotecaConstant.RESOURCE_AUTHORS_AUTHOR)
	public ResponseEntity<List<AuthorDTO>> findByKeyWordSQL(@RequestParam String key_word) {
		return new ResponseEntity<List<AuthorDTO>>(this.authorService.findByKeyWordSQL(key_word), HttpStatus.OK);
	}
	
	@PostMapping(BibliotecaConstant.RESOURCE_AUTHORS + BibliotecaConstant.RESOURCE_AUTHORS_AUTHOR)
	public ResponseEntity<HrefEntityDTO> saveSQL(@Valid @RequestBody AuthorDTORequest dto) {
		log.info("crce controller saveSQL -> {} "+dto.toString());
		return new ResponseEntity<HrefEntityDTO>(this.authorService.saveSQL(dto), HttpStatus.CREATED);
	}

}
