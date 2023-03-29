package com.pe.crce.biblioteca.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.pe.crce.biblioteca.dto.HrefEntityDTO;
import com.pe.crce.biblioteca.dto.SpecimenStateDTO;
import com.pe.crce.biblioteca.dto.request.SpecimenStateDTORequest;

public interface SpecimenStateService {
	public HrefEntityDTO save(SpecimenStateDTORequest dto);

	public HrefEntityDTO update(SpecimenStateDTORequest dto, Long id);

	public HrefEntityDTO delete(Long id);

	public SpecimenStateDTO findById(Long id);

	public Page<SpecimenStateDTO> findByDescription(String description, Pageable pageable);
}
