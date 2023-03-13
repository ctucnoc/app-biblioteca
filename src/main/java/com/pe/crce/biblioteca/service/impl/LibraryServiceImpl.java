package com.pe.crce.biblioteca.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import com.pe.crce.biblioteca.constant.BibliotecaConstant;
import com.pe.crce.biblioteca.dto.LibraryDTO;
import com.pe.crce.biblioteca.model.Library;
import com.pe.crce.biblioteca.repository.LibraryRepositorio;
import com.pe.crce.biblioteca.service.LibraryService;

@Service
public class LibraryServiceImpl implements LibraryService {
	
	private LibraryRepositorio libraryRepositorio;
	
	public LibraryServiceImpl(LibraryRepositorio libraryRepositorio) {
		this.libraryRepositorio = libraryRepositorio;
	}

	@Override
	public List<LibraryDTO> findByNameLikeJPA(String key_word) {
		List<Library> libraries = this.libraryRepositorio.findByNameLikeJPA(key_word, BibliotecaConstant.STATE_ACTIVE);
		return libraries.stream()
				.map((bean->convertBeanToDto(bean)))
				.collect(Collectors.toList());
	}

	public LibraryDTO convertBeanToDto(Library library) {
		LibraryDTO dto = new LibraryDTO();
		dto.setId(library.getId());
		dto.setName(library.getName());
		dto.setAddress(library.getAddress());
		dto.setDescription(library.getDescription());
		return dto;
	}

}
