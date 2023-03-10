package com.pe.crce.biblioteca.service;

import java.util.List;
import com.pe.crce.biblioteca.dto.EditorialDTO;

public interface LibraryService {
	public List<EditorialDTO> findByNameLikeJPA(String key_word);
}
