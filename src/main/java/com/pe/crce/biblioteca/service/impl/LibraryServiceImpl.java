package com.pe.crce.biblioteca.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import com.pe.crce.biblioteca.dto.EditorialDTO;
import com.pe.crce.biblioteca.service.LibraryService;

@Service
public class LibraryServiceImpl implements LibraryService{

	@Override
	public List<EditorialDTO> findByNameLikeJPA(String key_word) {
		// TODO Auto-generated method stub
		return null;
	}

}
