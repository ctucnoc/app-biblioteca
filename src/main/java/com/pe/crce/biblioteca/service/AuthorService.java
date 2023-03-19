package com.pe.crce.biblioteca.service;

import java.util.List;
import com.pe.crce.biblioteca.dto.AuthorDTO;
import com.pe.crce.biblioteca.dto.HrefEntityDTO;
import com.pe.crce.biblioteca.dto.request.AuthorDTORequest;

public interface AuthorService {
	public List<AuthorDTO> findByKeyWordSQL(String key_word);
	public HrefEntityDTO saveSQL(AuthorDTORequest dto);
}
