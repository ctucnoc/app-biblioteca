package com.pe.crce.biblioteca.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.pe.crce.biblioteca.dto.HrefEntityDTO;
import com.pe.crce.biblioteca.dto.LibraryDTO;
import com.pe.crce.biblioteca.dto.request.LibraryDTORequest;

public interface LibraryService {
	public Page<LibraryDTO> findByNameLikeJPA(String key_word, Pageable pageable);
	public HrefEntityDTO save(LibraryDTORequest dto);
	public HrefEntityDTO update(LibraryDTORequest dto, Long id);
	public HrefEntityDTO delete(Long id);
	public LibraryDTO findById(Long id);
}
