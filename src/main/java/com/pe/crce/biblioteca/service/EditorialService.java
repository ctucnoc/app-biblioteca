package com.pe.crce.biblioteca.service;

import java.util.List;

import com.pe.crce.biblioteca.dto.EditorialDTO;
import com.pe.crce.biblioteca.dto.request.EditorialDTORequest;
import com.pe.crce.biblioteca.model.Editorial;

public interface EditorialService {

	public Long save(EditorialDTORequest dto);
	
	public Long update(EditorialDTORequest dto,Long id);
	
	public EditorialDTO findById(Long id);
	
	public List<EditorialDTO> findaLL();
}
