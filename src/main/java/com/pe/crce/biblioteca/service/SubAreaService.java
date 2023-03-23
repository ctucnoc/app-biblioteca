package com.pe.crce.biblioteca.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.pe.crce.biblioteca.dto.HrefEntityDTO;
import com.pe.crce.biblioteca.dto.SubAreaDTO;
import com.pe.crce.biblioteca.dto.request.SubAreaDTORequest;

public interface SubAreaService {

	public SubAreaDTO findById(Long id);
	
	public HrefEntityDTO save(SubAreaDTORequest dto);
	
	public Page<SubAreaDTO> findByDescription(String description, Pageable pageable);
}
