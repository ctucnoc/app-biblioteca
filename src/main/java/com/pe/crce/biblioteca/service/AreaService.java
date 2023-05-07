package com.pe.crce.biblioteca.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.pe.crce.biblioteca.dto.AreaDTO;
import com.pe.crce.biblioteca.dto.HrefEntityDTO;
import com.pe.crce.biblioteca.dto.request.AreaDTORequest;

public interface AreaService {

	public HrefEntityDTO save(AreaDTORequest dto);
	
	public HrefEntityDTO update(AreaDTORequest dto,Long id);
	
	public HrefEntityDTO delete(Long id);
	
	public Page<AreaDTO> findByDescription(String description, Pageable pageable);
	
	public AreaDTO findById(Long id);
	
	public List<AreaDTO> findByDescriptionFilter(String description);
}
