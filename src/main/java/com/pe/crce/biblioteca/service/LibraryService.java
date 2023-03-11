package com.pe.crce.biblioteca.service;

import java.util.List;
import com.pe.crce.biblioteca.dto.LibraryDTO;

public interface LibraryService {
	public List<LibraryDTO> findByNameLikeJPA(String key_word);
}
