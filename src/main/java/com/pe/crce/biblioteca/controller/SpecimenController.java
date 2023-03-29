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
import com.pe.crce.biblioteca.dto.HrefEntityDTO;
import com.pe.crce.biblioteca.dto.PageableDTO;
import com.pe.crce.biblioteca.dto.SpecimenDTO;
import com.pe.crce.biblioteca.dto.request.SpecimenDTORequest;
import com.pe.crce.biblioteca.service.SpecimenService;
import com.pe.crce.biblioteca.util.BibliotecaUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(BibliotecaConstant.RESOURCE_GENERIC)
public class SpecimenController {

	final
	SpecimenService specimenService;
	
	final
	BibliotecaUtil util;
	
	public SpecimenController(SpecimenService specimenService, BibliotecaUtil util) {
		this.specimenService = specimenService;
		this.util = util;
	}

	@GetMapping(BibliotecaConstant.RESOURCE_SPECIMENS + BibliotecaConstant.RESOURCE_SPECIMENS_SPECIMEN)
	public ResponseEntity<Page<SpecimenDTO>> findByDescription(@RequestParam String key_word,PageableDTO pageable){
		log.info("crce ZonaController findByDescription -> {} "+pageable.toString());
		return new ResponseEntity<Page<SpecimenDTO>>(this.specimenService.findByKeyWordJPQL(key_word,this.util.getPageable(pageable)), HttpStatus.OK);
	}	

	@GetMapping(BibliotecaConstant.RESOURCE_SPECIMENS + BibliotecaConstant.RESOURCE_SPECIMENS_SPECIMEN + BibliotecaConstant.RESOURCE_GENERIC_ID)
	public ResponseEntity<SpecimenDTO> findById(@PathVariable Long id) {
		log.info("crce controller findById -> {} "+id);
		return new ResponseEntity<SpecimenDTO>(this.specimenService.findById(id), HttpStatus.OK);
	}
	
	@PostMapping(BibliotecaConstant.RESOURCE_SPECIMENS + BibliotecaConstant.RESOURCE_SPECIMENS_SPECIMEN)
	public ResponseEntity<HrefEntityDTO> save(@RequestBody @Valid SpecimenDTORequest dto) {
		log.info("crce controller save -> {} "+dto.toString());
		return new ResponseEntity<HrefEntityDTO>(this.specimenService.save(dto), HttpStatus.CREATED);
	}
	
	@PutMapping(BibliotecaConstant.RESOURCE_SPECIMENS + BibliotecaConstant.RESOURCE_SPECIMENS_SPECIMEN
			+ BibliotecaConstant.RESOURCE_GENERIC_ID)
	public ResponseEntity<HrefEntityDTO> update(@RequestBody SpecimenDTORequest dto, @PathVariable Long id) {
		log.info("crce controller update -> {} "+dto.toString());
		return new ResponseEntity<HrefEntityDTO>(this.specimenService.update(dto, id), HttpStatus.OK);
	}

	@DeleteMapping(BibliotecaConstant.RESOURCE_SPECIMENS + BibliotecaConstant.RESOURCE_SPECIMENS_SPECIMEN
			+ BibliotecaConstant.RESOURCE_GENERIC_ID)
	public ResponseEntity<HrefEntityDTO> delete(@PathVariable Long id) {
		log.info("crce controller delete -> {} "+id);
		return new ResponseEntity<HrefEntityDTO>(this.specimenService.delete(id), HttpStatus.OK);
	}
}
