package com.pe.crce.biblioteca.service;

import java.io.File;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.pe.crce.biblioteca.dto.BookDTO;
import com.pe.crce.biblioteca.dto.HrefEntityDTO;
import com.pe.crce.biblioteca.dto.request.BookDTORequest;

public interface BookService {

	public HrefEntityDTO save(BookDTORequest dto);
	public HrefEntityDTO update(BookDTORequest dto, Long id);
	public HrefEntityDTO delete(Long id);
	public BookDTO findById(Long id);
	public Page<BookDTO> findByKeyWordJPQL(String key_word, Pageable pageable);
	public Boolean existsByIsbn(String isbn);
	
	public File generateExcel(List<BookDTO> books);
}
