package com.pe.crce.biblioteca.controller;

import java.io.File;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
import com.pe.crce.biblioteca.dto.BookDTO;
import com.pe.crce.biblioteca.dto.HrefEntityDTO;
import com.pe.crce.biblioteca.dto.PageableDTO;
import com.pe.crce.biblioteca.dto.request.BookDTORequest;
import com.pe.crce.biblioteca.service.BookService;
import com.pe.crce.biblioteca.util.BibliotecaUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(BibliotecaConstant.RESOURCE_GENERIC)
public class BookController {

	final
	BookService bookService;
	
	final 
	BibliotecaUtil util;
	
	
	public BookController(BookService bookService, BibliotecaUtil util) {
		this.bookService = bookService;
		this.util = util;
	}
	
	@GetMapping(BibliotecaConstant.RESOURCE_BOOKS + BibliotecaConstant.RESOURCE_BOOKS_BOOK)
	public ResponseEntity<Page<BookDTO>> findByKeyWord(@RequestParam String key_word, PageableDTO pageable){
		log.info("crce BookController findByKeyWord -> {} "+pageable.toString());
		return new ResponseEntity<Page<BookDTO>>(this.bookService.findByKeyWordJPQL(key_word, this.util.getPageable(pageable)), HttpStatus.OK);
	}
	
	@GetMapping(BibliotecaConstant.RESOURCE_BOOKS + BibliotecaConstant.RESOURCE_BOOKS_BOOK + BibliotecaConstant.RESOURCE_GENERIC_ID)
	public ResponseEntity<BookDTO> findById(@PathVariable Long id) {
		return new ResponseEntity<BookDTO>(this.bookService.findById(id), HttpStatus.OK);
	}	
	
	@PostMapping(BibliotecaConstant.RESOURCE_BOOKS + BibliotecaConstant.RESOURCE_BOOKS_BOOK)
	public ResponseEntity<HrefEntityDTO> save(@RequestBody @Valid BookDTORequest dto) {
		return new ResponseEntity<HrefEntityDTO>(this.bookService.save(dto), HttpStatus.CREATED);
	}
	
	@PutMapping(BibliotecaConstant.RESOURCE_BOOKS + BibliotecaConstant.RESOURCE_BOOKS_BOOK + BibliotecaConstant.RESOURCE_GENERIC_ID)
	public ResponseEntity<HrefEntityDTO> update(@RequestBody BookDTORequest dto,@PathVariable Long id) {
		return new ResponseEntity<HrefEntityDTO>(this.bookService.update(dto, id), HttpStatus.OK);
	}
	
	@DeleteMapping(BibliotecaConstant.RESOURCE_BOOKS + BibliotecaConstant.RESOURCE_BOOKS_BOOK + BibliotecaConstant.RESOURCE_GENERIC_ID)
	public ResponseEntity<HrefEntityDTO> delete(@PathVariable Long id) {
		log.info("crce controler delete -> {} "+id);
		return new ResponseEntity<HrefEntityDTO>(this.bookService.delete(id), HttpStatus.OK);
	}
	
	@GetMapping(BibliotecaConstant.RESOURCE_BOOKS + BibliotecaConstant.RESOURCE_BOOKS_BOOK + BibliotecaConstant.RESOURCE_GENERIC_FILTER)
	public ResponseEntity<Boolean> existByIsbn(@RequestParam(required = true) @NotBlank String isbn){
		log.info("crce controler existByIsbn -> {} "+isbn);
		return new ResponseEntity<Boolean>(this.bookService.existsByIsbn(isbn),HttpStatus.OK);
	}
	
	@GetMapping(BibliotecaConstant.RESOURCE_BOOKS + BibliotecaConstant.RESOURCE_BOOKS_BOOK + BibliotecaConstant.RESOURCE_EXPORT_EXCEL)
	public ResponseEntity<Resource> generateExcel(@RequestParam String key_word, PageableDTO pageable){
		Page<BookDTO> pages = this.bookService.findByKeyWordJPQL(key_word, this.util.getPageable(pageable));
		File file = this.bookService.generateExcel(pages.getContent());
		
		// configuracion de la cabecera de las respuestas
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		headers.setContentDispositionFormData("attachment", file.getName());
		
        // Crear la respuesta HTTP con el objeto File
        FileSystemResource fileResource = new FileSystemResource(file);
        return new ResponseEntity<Resource>(fileResource, headers, HttpStatus.OK);

}
}
