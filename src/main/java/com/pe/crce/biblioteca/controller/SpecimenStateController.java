package com.pe.crce.biblioteca.controller;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
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
import com.pe.crce.biblioteca.dto.HrefEntityDTO;
import com.pe.crce.biblioteca.dto.PageableDTO;
import com.pe.crce.biblioteca.dto.SpecimenStateDTO;
import com.pe.crce.biblioteca.dto.request.SpecimenStateDTORequest;
import com.pe.crce.biblioteca.service.SpecimenStateService;
import com.pe.crce.biblioteca.util.BibliotecaUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(BibliotecaConstant.RESOURCE_GENERIC)
public class SpecimenStateController {

	final
	SpecimenStateService specimenStateService;
	
	final
	BibliotecaUtil util;

	public SpecimenStateController(SpecimenStateService specimenStateService, BibliotecaUtil util) {
		this.specimenStateService = specimenStateService;
		this.util = util;
	}

	@GetMapping(BibliotecaConstant.RESOURCE_SPECIMENSTATES + BibliotecaConstant.RESOURCE_SPECIMENSTATES_SPECIMENSTATE)
	public ResponseEntity<Page<SpecimenStateDTO>> findByDescription(@RequestParam String description,PageableDTO pageable){
		log.info("crce SpecimenStateController findByDescription -> {} "+pageable.toString());
		return new ResponseEntity<Page<SpecimenStateDTO>>(this.specimenStateService.findByDescription(description,this.util.getPageable(pageable)), HttpStatus.OK);
	}
	

	@GetMapping(BibliotecaConstant.RESOURCE_SPECIMENSTATES + BibliotecaConstant.RESOURCE_SPECIMENSTATES_SPECIMENSTATE + BibliotecaConstant.RESOURCE_GENERIC_ID)
	public ResponseEntity<SpecimenStateDTO> findById(@PathVariable(required = true, name = "id") @NotNull Long id) {
		log.info("crce controller findById -> {} "+id);
		return new ResponseEntity<SpecimenStateDTO>(this.specimenStateService.findById(id), HttpStatus.OK);
	}
	
	@PostMapping(BibliotecaConstant.RESOURCE_SPECIMENSTATES + BibliotecaConstant.RESOURCE_SPECIMENSTATES_SPECIMENSTATE)
	public ResponseEntity<HrefEntityDTO> save(@RequestBody @Valid SpecimenStateDTORequest dto) {
		log.info("crce controller save -> {} "+dto.toString());
		return new ResponseEntity<HrefEntityDTO>(this.specimenStateService.save(dto), HttpStatus.CREATED);
	}
	
	@PutMapping(BibliotecaConstant.RESOURCE_SPECIMENSTATES + BibliotecaConstant.RESOURCE_SPECIMENSTATES_SPECIMENSTATE
			+ BibliotecaConstant.RESOURCE_GENERIC_ID)
	public ResponseEntity<HrefEntityDTO> update(@RequestBody SpecimenStateDTORequest dto, @PathVariable Long id) {
		log.info("crce controller update -> {} "+dto.toString());
		return new ResponseEntity<HrefEntityDTO>(this.specimenStateService.update(dto, id), HttpStatus.OK);
	}

	@DeleteMapping(BibliotecaConstant.RESOURCE_SPECIMENSTATES + BibliotecaConstant.RESOURCE_SPECIMENSTATES_SPECIMENSTATE
			+ BibliotecaConstant.RESOURCE_GENERIC_ID)
	public ResponseEntity<HrefEntityDTO> delete(@PathVariable(required = true, name = "id") @NotNull Long id) {
		log.info("crce controller delete -> {} "+id);
		return new ResponseEntity<HrefEntityDTO>(this.specimenStateService.delete(id), HttpStatus.OK);
	}
}
