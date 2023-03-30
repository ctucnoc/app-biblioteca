package com.pe.crce.biblioteca.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.pe.crce.biblioteca.dto.BookAuthorDTO;
import com.pe.crce.biblioteca.dto.HrefEntityDTO;
import com.pe.crce.biblioteca.dto.request.BookAuthorDTORequest;

public interface BookAuthorService {

	public HrefEntityDTO save(BookAuthorDTORequest dto);
	public HrefEntityDTO delete(Long id);
	public Page<BookAuthorDTO> findByBook(Long idBook, Pageable pageable);
}
