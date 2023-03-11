package com.pe.crce.biblioteca.controller;

import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.pe.crce.biblioteca.constant.BibliotecaConstant;
import com.pe.crce.biblioteca.dto.LibraryDTO;
import com.pe.crce.biblioteca.service.LibraryService;

@RestController
@RequestMapping(BibliotecaConstant.RESOURCE_GENERIC)
@CrossOrigin(BibliotecaConstant.CLIENT_FRONTEND)
public class LibraryController {
	
	final
	LibraryService libraryService;
	
	public LibraryController(LibraryService libraryService) {
		super();
		this.libraryService = libraryService;
	}

	@GetMapping(BibliotecaConstant.RESOURCE_LIBRARYS + BibliotecaConstant.RESOURCE_LIBRARYS_LIBRARY)
	public List<LibraryDTO> findByNameLikeJPA(@RequestParam String key_word) {
		return this.libraryService.findByNameLikeJPA(key_word);
	}

}
