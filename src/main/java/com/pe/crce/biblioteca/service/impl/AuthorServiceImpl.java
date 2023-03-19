package com.pe.crce.biblioteca.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import com.pe.crce.biblioteca.constant.BibliotecaConstant;
import com.pe.crce.biblioteca.dto.AuthorDTO;
import com.pe.crce.biblioteca.dto.HrefEntityDTO;
import com.pe.crce.biblioteca.dto.request.AuthorDTORequest;
import com.pe.crce.biblioteca.mapper.AuthorMapper;
import com.pe.crce.biblioteca.model.Author;
import com.pe.crce.biblioteca.repository.AuthorRepository;
import com.pe.crce.biblioteca.service.AuthorService;
import com.pe.crce.biblioteca.util.BibliotecaResource;
import com.pe.crce.biblioteca.util.BibliotecaUtil;

@Service
public class AuthorServiceImpl implements AuthorService{

	final AuthorRepository authorRepository;
	
	final
	AuthorMapper authorMapper;
	
	final
	BibliotecaUtil util;
		
	public AuthorServiceImpl(AuthorRepository authorRepository,AuthorMapper authorMapper,BibliotecaUtil util) {
		this.authorRepository = authorRepository;
		this.authorMapper = authorMapper;
		this.util = util;
	}

	@Override
	public List<AuthorDTO> findByKeyWordSQL(String key_word) {
		List<Author> list = this.authorRepository.findByKeyWordSQL(key_word, BibliotecaConstant.STATE_ACTIVE);
		return list.stream()
				.map((bean)->authorMapper.toDto(bean))
				.collect(Collectors.toList());
	}

	@Override
	public HrefEntityDTO saveSQL(AuthorDTORequest dto) {
		Author author = this.authorRepository.saveSQL(dto.getName(), dto.getLastName());
		return util.createHrefFromResource(author.getId(), BibliotecaResource.AUTHOR);
	}
	
}
