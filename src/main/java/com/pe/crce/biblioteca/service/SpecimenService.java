package com.pe.crce.biblioteca.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.pe.crce.biblioteca.dto.HrefEntityDTO;
import com.pe.crce.biblioteca.dto.SpecimenDTO;
import com.pe.crce.biblioteca.dto.request.SpecimenDTORequest;

public interface SpecimenService {
	public HrefEntityDTO save(SpecimenDTORequest dto);

	public HrefEntityDTO update(SpecimenDTORequest dto, Long id);

	public HrefEntityDTO delete(Long id);

	public Page<SpecimenDTO> findByKeyWordJPQL(String key_word, Pageable pageable);

	public SpecimenDTO findById(Long id);
}
