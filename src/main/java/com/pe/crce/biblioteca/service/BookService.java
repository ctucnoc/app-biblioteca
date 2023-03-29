package com.pe.crce.biblioteca.service;

import com.pe.crce.biblioteca.dto.BookDTO;
import com.pe.crce.biblioteca.dto.HrefEntityDTO;
import com.pe.crce.biblioteca.dto.request.BookDTORequest;

public interface BookService {

	public HrefEntityDTO save(BookDTORequest dto);
	public HrefEntityDTO update(BookDTORequest dto, Long id);
	public HrefEntityDTO delete(Long id);
	public BookDTO findById(Long id);
}
