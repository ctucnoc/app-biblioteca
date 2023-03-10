package com.pe.crce.biblioteca.controller;

import javax.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.pe.crce.biblioteca.dto.EditorialDTO;
import com.pe.crce.biblioteca.dto.PageableDTO;
import com.pe.crce.biblioteca.dto.request.EditorialDTORequest;
import com.pe.crce.biblioteca.service.EditorialService;
import com.pe.crce.biblioteca.util.BibliotecaUtil;

import lombok.extern.slf4j.Slf4j;

import com.pe.crce.biblioteca.constant.BibliotecaConstant;

@Slf4j
@RestController
@RequestMapping(BibliotecaConstant.RESOURCE_GENERIC)
@CrossOrigin(BibliotecaConstant.CLIENT_FRONTEND)
public class EditorialController {

	final
	EditorialService editorialService;
	
	final BibliotecaUtil util;

	public EditorialController(EditorialService editorialService,BibliotecaUtil util) {
		this.editorialService = editorialService;
		this.util = util;
	}
	
	@GetMapping(BibliotecaConstant.RESOURCE_EDITORIALS + BibliotecaConstant.RESOURCE_EDITORIALS_EDITORIAL)
	public Page<EditorialDTO> findByName(@RequestParam String name,@Valid PageableDTO pageable){
		log.info("crce EditorialController -> {} "+pageable);
		return this.editorialService.findByNameLike(name, this.util.getPageable(pageable));
	}
	
	@GetMapping(BibliotecaConstant.RESOURCE_EDITORIALS + BibliotecaConstant.RESOURCE_EDITORIALS_EDITORIAL + BibliotecaConstant.RESOURCE_GENERIC_ID)
	public EditorialDTO findById(@PathVariable Long id) {
		return this.editorialService.findById(id);
	}	
	
	@PostMapping(BibliotecaConstant.RESOURCE_EDITORIALS + BibliotecaConstant.RESOURCE_EDITORIALS_EDITORIAL)
	public Long save(@RequestBody EditorialDTORequest dto) {
		return this.editorialService.save(dto);
	}
	
	@PutMapping(BibliotecaConstant.RESOURCE_EDITORIALS + BibliotecaConstant.RESOURCE_EDITORIALS_EDITORIAL + BibliotecaConstant.RESOURCE_GENERIC_ID)
	public Long update(@RequestBody EditorialDTORequest dto,@PathVariable Long id) {
		return this.editorialService.update(dto, id);
	}

	
}
