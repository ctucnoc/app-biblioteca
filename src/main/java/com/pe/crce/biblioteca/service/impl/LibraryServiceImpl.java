package com.pe.crce.biblioteca.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import com.pe.crce.biblioteca.constant.BibliotecaConstant;
import com.pe.crce.biblioteca.dto.LibraryDTO;
import com.pe.crce.biblioteca.mapper.LibraryMapper;
import com.pe.crce.biblioteca.model.Library;
import com.pe.crce.biblioteca.repository.LibraryRepositorio;
import com.pe.crce.biblioteca.service.LibraryService;

@Service
public class LibraryServiceImpl implements LibraryService {
	
	final
	LibraryRepositorio libraryRepositorio;
	
	final
	LibraryMapper libraryMapper;
	
	public LibraryServiceImpl(LibraryRepositorio libraryRepositorio,LibraryMapper libraryMapper) {
		this.libraryRepositorio = libraryRepositorio;
		this.libraryMapper = libraryMapper;
	}

	@Override
	public List<LibraryDTO> findByNameLikeJPA(String key_word) {
		List<Library> libraries = this.libraryRepositorio.findByNameLikeJPA(key_word, BibliotecaConstant.STATE_ACTIVE);
		return libraries.stream()
				.map((bean->libraryMapper.toDto(bean)))
				.collect(Collectors.toList());
	}

}
