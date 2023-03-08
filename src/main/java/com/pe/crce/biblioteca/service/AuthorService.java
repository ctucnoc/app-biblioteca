package com.pe.crce.biblioteca.service;

import java.util.List;
import com.pe.crce.biblioteca.dto.AuthorDTO;

public interface AuthorService {
	public List<AuthorDTO> findByKeyWordSQL(String key_word);
}
