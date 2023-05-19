package com.pe.crce.biblioteca.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.pe.crce.biblioteca.dto.HrefEntityDTO;
import com.pe.crce.biblioteca.dto.ZoneDTO;
import com.pe.crce.biblioteca.dto.request.ZoneDTORequest;

public interface ZonaService {

	public HrefEntityDTO save(ZoneDTORequest dto);
	public HrefEntityDTO update(ZoneDTORequest dto,Long id);
	public HrefEntityDTO delete(Long id);
	public Page<ZoneDTO> findByDescription(String description,Pageable pageable);
	public Page<ZoneDTO> findByLibrary(Long idLibrary,Pageable pageable);
	public ZoneDTO findById(Long id);
}
