package com.pe.crce.biblioteca.controller;

import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
import com.pe.crce.biblioteca.dto.HrefEntityDTO;
import com.pe.crce.biblioteca.dto.LibraryDTO;
import com.pe.crce.biblioteca.dto.request.LibraryDTORequest;
import com.pe.crce.biblioteca.service.LibraryService;
import com.pe.crce.biblioteca.util.BibliotecaUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(BibliotecaConstant.RESOURCE_GENERIC)
public class LibraryController {

	final 
	LibraryService libraryService;

	final 
	BibliotecaUtil util;

	public LibraryController(LibraryService libraryService, BibliotecaUtil util) {
		this.libraryService = libraryService;
		this.util = util;
	}

	@GetMapping(BibliotecaConstant.RESOURCE_LIBRARYS + BibliotecaConstant.RESOURCE_LIBRARYS_LIBRARY)
	public ResponseEntity<Page<LibraryDTO>> findByNameLikeJPA(@RequestParam String key_word, Pageable pageable) {
		log.info("crce LibraryController findByNameLikeJPA -> {} " + pageable.toString());
		return new ResponseEntity<Page<LibraryDTO>>(this.libraryService.findByNameLikeJPA(key_word, pageable),
				HttpStatus.OK);
	}

	@GetMapping(BibliotecaConstant.RESOURCE_LIBRARYS + BibliotecaConstant.RESOURCE_LIBRARYS_LIBRARY
			+ BibliotecaConstant.RESOURCE_GENERIC_ID)
	public ResponseEntity<LibraryDTO> findById(@PathVariable Long id) {
		log.info("crce LibraryController findById -> {} " + id);
		return new ResponseEntity<LibraryDTO>(this.libraryService.findById(id), HttpStatus.OK);
	}

	@PostMapping(BibliotecaConstant.RESOURCE_LIBRARYS + BibliotecaConstant.RESOURCE_LIBRARYS_LIBRARY)
	public ResponseEntity<HrefEntityDTO> save(@RequestBody @Valid LibraryDTORequest dto) {
		log.info("crce LibraryController save -> {} " + dto.toString());
		return new ResponseEntity<HrefEntityDTO>(this.libraryService.save(dto), HttpStatus.CREATED);
	}

	@PutMapping(BibliotecaConstant.RESOURCE_LIBRARYS + BibliotecaConstant.RESOURCE_LIBRARYS_LIBRARY
			+ BibliotecaConstant.RESOURCE_GENERIC_ID)
	public ResponseEntity<HrefEntityDTO> update(@RequestBody LibraryDTORequest dto, @PathVariable Long id) {
		log.info("crce LibraryController update -> {} " + dto.toString());
		return new ResponseEntity<HrefEntityDTO>(this.libraryService.update(dto, id), HttpStatus.OK);
	}

	@DeleteMapping(BibliotecaConstant.RESOURCE_LIBRARYS + BibliotecaConstant.RESOURCE_LIBRARYS_LIBRARY
			+ BibliotecaConstant.RESOURCE_GENERIC_ID)
	public ResponseEntity<HrefEntityDTO> delete(@PathVariable Long id) {
		log.info("crce LibraryController delete -> {} " + id);
		return new ResponseEntity<HrefEntityDTO>(this.libraryService.delete(id), HttpStatus.OK);
	}
	
	@GetMapping(BibliotecaConstant.RESOURCE_LIBRARYS + BibliotecaConstant.RESOURCE_LIBRARYS_LIBRARY + BibliotecaConstant.RESOURCE_GENERIC_FILTER)
	public ResponseEntity<List<LibraryDTO>> findByFilter(@RequestParam(required = true) @NotBlank String key_word){
		return new ResponseEntity<List<LibraryDTO>>(this.libraryService.findByKeyWordJPA(key_word), HttpStatus.OK);
	}

}
