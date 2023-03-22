package com.pe.crce.biblioteca.controller;

import javax.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.pe.crce.biblioteca.dto.EditorialDTO;
import com.pe.crce.biblioteca.dto.HrefEntityDTO;
import com.pe.crce.biblioteca.dto.PageableDTO;
import com.pe.crce.biblioteca.dto.request.EditorialDTORequest;
import com.pe.crce.biblioteca.service.EditorialService;
import com.pe.crce.biblioteca.util.BibliotecaUtil;
import lombok.extern.slf4j.Slf4j;
import com.pe.crce.biblioteca.constant.BibliotecaConstant;

@Slf4j
@RestController
@RequestMapping(BibliotecaConstant.RESOURCE_GENERIC)
public class EditorialController {

	final
	EditorialService editorialService;
	
	final
	BibliotecaUtil util;

	public EditorialController(EditorialService editorialService,BibliotecaUtil util) {
		this.editorialService = editorialService;
		this.util = util;
	}
	
	@GetMapping(BibliotecaConstant.RESOURCE_EDITORIALS + BibliotecaConstant.RESOURCE_EDITORIALS_EDITORIAL)
	public ResponseEntity<Page<EditorialDTO>> findByName(@RequestParam String name,PageableDTO pageable){
		log.info("controller -> {} "+pageable.toString());
		return new ResponseEntity<Page<EditorialDTO>>(this.editorialService.findByNameLike(name,this.util.getPageable(pageable)), HttpStatus.OK);
	}
	
	@GetMapping(BibliotecaConstant.RESOURCE_EDITORIALS + BibliotecaConstant.RESOURCE_EDITORIALS_EDITORIAL + BibliotecaConstant.RESOURCE_GENERIC_ID)
	public ResponseEntity<EditorialDTO> findById(@PathVariable Long id) {
		return new ResponseEntity<EditorialDTO>(this.editorialService.findById(id), HttpStatus.OK);
	}	
	
	@PostMapping(BibliotecaConstant.RESOURCE_EDITORIALS + BibliotecaConstant.RESOURCE_EDITORIALS_EDITORIAL)
	public ResponseEntity<HrefEntityDTO> save(@RequestBody @Valid EditorialDTORequest dto) {
		return new ResponseEntity<HrefEntityDTO>(this.editorialService.save(dto), HttpStatus.OK);
	}
	
	@PutMapping(BibliotecaConstant.RESOURCE_EDITORIALS + BibliotecaConstant.RESOURCE_EDITORIALS_EDITORIAL + BibliotecaConstant.RESOURCE_GENERIC_ID)
	public ResponseEntity<HrefEntityDTO> update(@RequestBody EditorialDTORequest dto,@PathVariable Long id) {
		return new ResponseEntity<HrefEntityDTO>(this.editorialService.update(dto, id), HttpStatus.OK);
	}

	
}
