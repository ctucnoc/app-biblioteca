package com.pe.crce.biblioteca.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.pe.crce.biblioteca.dto.EditorialDTO;
import com.pe.crce.biblioteca.dto.HrefEntityDTO;
import com.pe.crce.biblioteca.dto.request.EditorialDTORequest;

public interface EditorialService {

	public HrefEntityDTO save(EditorialDTORequest dto);
	
	public HrefEntityDTO update(EditorialDTORequest dto,Long id);
	
	public EditorialDTO findById(Long id);
	
	public List<EditorialDTO> findaLL();
	
	public Page<EditorialDTO> findByNameLike(String name,Pageable pageable);
}
