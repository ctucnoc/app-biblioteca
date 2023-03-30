package com.pe.crce.biblioteca.controller;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.pe.crce.biblioteca.constant.BibliotecaConstant;
import com.pe.crce.biblioteca.dto.BookAuthorDTO;
import com.pe.crce.biblioteca.dto.HrefEntityDTO;
import com.pe.crce.biblioteca.dto.PageableDTO;
import com.pe.crce.biblioteca.dto.request.BookAuthorDTORequest;
import com.pe.crce.biblioteca.service.BookAuthorService;
import com.pe.crce.biblioteca.util.BibliotecaUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(BibliotecaConstant.RESOURCE_GENERIC)
public class BookAuthorController {

	final
	BookAuthorService bookAuthorService;
	
	final
	BibliotecaUtil util;

	public BookAuthorController(BookAuthorService bookAuthorService, BibliotecaUtil util) {
		this.bookAuthorService = bookAuthorService;
		this.util = util;
	}
	
	@GetMapping(BibliotecaConstant.RESOURCE_BOOKAUTHORS + BibliotecaConstant.RESOURCE_BOOKAUTHORS_BOOKAUTHOR+BibliotecaConstant.RESOURCE_GENERIC_ID)
	public ResponseEntity<Page<BookAuthorDTO>> findByBook(@PathVariable Long id,PageableDTO pageable){
		log.info("crce BookAuthorController findByBook -> {} "+pageable.toString());
		return new ResponseEntity<Page<BookAuthorDTO>>(this.bookAuthorService.findByBook(id,this.util.getPageable(pageable)), HttpStatus.OK);
	}
	
	@PostMapping(BibliotecaConstant.RESOURCE_BOOKAUTHORS + BibliotecaConstant.RESOURCE_BOOKAUTHORS_BOOKAUTHOR)
	public ResponseEntity<HrefEntityDTO> save(@RequestBody @Valid BookAuthorDTORequest dto) {
		log.info("crce BookAuthorController save -> {} "+dto.toString());
		return new ResponseEntity<HrefEntityDTO>(this.bookAuthorService.save(dto), HttpStatus.CREATED);
	}
	
	@DeleteMapping(BibliotecaConstant.RESOURCE_BOOKAUTHORS + BibliotecaConstant.RESOURCE_BOOKAUTHORS_BOOKAUTHOR
			+ BibliotecaConstant.RESOURCE_GENERIC_ID)
	public ResponseEntity<HrefEntityDTO> delete(@PathVariable Long id) {
		log.info("crce BookAuthorController delete -> {} "+id);
		return new ResponseEntity<HrefEntityDTO>(this.bookAuthorService.delete(id), HttpStatus.OK);
	}	
	
}
