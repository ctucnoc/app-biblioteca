package com.pe.crce.biblioteca.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.pe.crce.biblioteca.dto.AuthorDTO;
import com.pe.crce.biblioteca.dto.HrefEntityDTO;
import com.pe.crce.biblioteca.dto.request.AuthorDTORequest;

public interface AuthorService {
	public Page<AuthorDTO> findByKeyWordSQL(String key_word,Pageable pageable);
	public HrefEntityDTO saveSQL(AuthorDTORequest dto);
	public HrefEntityDTO update(AuthorDTORequest dto, Long id);
	public HrefEntityDTO delete(Long id);
	public AuthorDTO findById(Long id);
}
