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
import com.pe.crce.biblioteca.dto.request.SubAreaDTORequest;
import com.pe.crce.biblioteca.service.SubAreaService;
import com.pe.crce.biblioteca.util.BibliotecaUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(BibliotecaConstant.RESOURCE_GENERIC)
public class SubAreaController {

	final
	SubAreaService subAreaService;
	
	final
	BibliotecaUtil util;
	
	@GetMapping(BibliotecaConstant.RESOURCE_SUBAREAS + BibliotecaConstant.RESOURCE_SUBAREAS_SUBAREA)
	public ResponseEntity<Page<SubAreaDTO>> findByDescription(@RequestParam String description,PageableDTO pageable){
		log.info("crce controller findByDescription -> {} "+pageable.toString());
		return new ResponseEntity<Page<SubAreaDTO>>(this.subAreaService.findByDescription(description,this.util.getPageable(pageable)), HttpStatus.OK);
	}
	
	public SubAreaController(SubAreaService subAreaService, BibliotecaUtil util) {
		this.subAreaService = subAreaService;
		this.util = util;
	}

	@GetMapping(BibliotecaConstant.RESOURCE_SUBAREAS + BibliotecaConstant.RESOURCE_SUBAREAS_SUBAREA + BibliotecaConstant.RESOURCE_GENERIC_ID)
	public ResponseEntity<SubAreaDTO> findById(@PathVariable Long id) {
		log.info("crce controller findById -> {} "+id);
		return new ResponseEntity<SubAreaDTO>(this.subAreaService.findById(id), HttpStatus.OK);
	}
	
	@PostMapping(BibliotecaConstant.RESOURCE_SUBAREAS + BibliotecaConstant.RESOURCE_SUBAREAS_SUBAREA)
	public ResponseEntity<HrefEntityDTO> save(@RequestBody @Valid SubAreaDTORequest dto) {
		log.info("crce controller save -> {} "+dto.toString());
		return new ResponseEntity<HrefEntityDTO>(this.subAreaService.save(dto), HttpStatus.CREATED);
	}
	
	@PutMapping(BibliotecaConstant.RESOURCE_SUBAREAS + BibliotecaConstant.RESOURCE_SUBAREAS_SUBAREA
			+ BibliotecaConstant.RESOURCE_GENERIC_ID)
	public ResponseEntity<HrefEntityDTO> update(@RequestBody SubAreaDTORequest dto, @PathVariable Long id) {
		log.info("crce controller update -> {} "+dto.toString());
		return new ResponseEntity<HrefEntityDTO>(this.subAreaService.update(dto, id), HttpStatus.OK);
	}

	@DeleteMapping(BibliotecaConstant.RESOURCE_SUBAREAS + BibliotecaConstant.RESOURCE_SUBAREAS_SUBAREA
			+ BibliotecaConstant.RESOURCE_GENERIC_ID)
	public ResponseEntity<HrefEntityDTO> delete(@PathVariable Long id) {
		log.info("crce controller delete -> {} "+id);
		return new ResponseEntity<HrefEntityDTO>(this.subAreaService.delete(id), HttpStatus.OK);
	}
}
