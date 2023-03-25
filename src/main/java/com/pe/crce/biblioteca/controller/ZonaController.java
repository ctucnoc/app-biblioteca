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
import com.pe.crce.biblioteca.dto.SubAreaDTO;
import com.pe.crce.biblioteca.dto.ZoneDTO;
import com.pe.crce.biblioteca.dto.request.SubAreaDTORequest;
import com.pe.crce.biblioteca.dto.request.ZoneDTORequest;
import com.pe.crce.biblioteca.service.SubAreaService;
import com.pe.crce.biblioteca.service.ZonaService;
import com.pe.crce.biblioteca.util.BibliotecaUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(BibliotecaConstant.RESOURCE_GENERIC)
public class ZonaController {

	final
	ZonaService zonaService;
	
	final
	BibliotecaUtil util;
	
	public ZonaController(ZonaService zonaService, BibliotecaUtil util) {
		this.zonaService = zonaService;
		this.util = util;
	}

	@GetMapping(BibliotecaConstant.RESOURCE_ZONES + BibliotecaConstant.RESOURCE_ZONES_ZONE)
	public ResponseEntity<Page<ZoneDTO>> findByDescription(@RequestParam String description,PageableDTO pageable){
		log.info("crce ZonaController findByDescription -> {} "+pageable.toString());
		return new ResponseEntity<Page<ZoneDTO>>(this.zonaService.findByDescription(description,this.util.getPageable(pageable)), HttpStatus.OK);
	}
	

	@GetMapping(BibliotecaConstant.RESOURCE_ZONES + BibliotecaConstant.RESOURCE_ZONES_ZONE + BibliotecaConstant.RESOURCE_GENERIC_ID)
	public ResponseEntity<ZoneDTO> findById(@PathVariable Long id) {
		log.info("crce controller findById -> {} "+id);
		return new ResponseEntity<ZoneDTO>(this.zonaService.findById(id), HttpStatus.OK);
	}
	
	@PostMapping(BibliotecaConstant.RESOURCE_ZONES + BibliotecaConstant.RESOURCE_ZONES_ZONE)
	public ResponseEntity<HrefEntityDTO> save(@RequestBody @Valid ZoneDTORequest dto) {
		log.info("crce controller save -> {} "+dto.toString());
		return new ResponseEntity<HrefEntityDTO>(this.zonaService.save(dto), HttpStatus.CREATED);
	}
	
	@PutMapping(BibliotecaConstant.RESOURCE_ZONES + BibliotecaConstant.RESOURCE_ZONES_ZONE
			+ BibliotecaConstant.RESOURCE_GENERIC_ID)
	public ResponseEntity<HrefEntityDTO> update(@RequestBody ZoneDTORequest dto, @PathVariable Long id) {
		log.info("crce controller update -> {} "+dto.toString());
		return new ResponseEntity<HrefEntityDTO>(this.zonaService.update(dto, id), HttpStatus.OK);
	}

	@DeleteMapping(BibliotecaConstant.RESOURCE_ZONES + BibliotecaConstant.RESOURCE_ZONES_ZONE
			+ BibliotecaConstant.RESOURCE_GENERIC_ID)
	public ResponseEntity<HrefEntityDTO> delete(@PathVariable Long id) {
		log.info("crce controller delete -> {} "+id);
		return new ResponseEntity<HrefEntityDTO>(this.zonaService.delete(id), HttpStatus.OK);
	}
}
