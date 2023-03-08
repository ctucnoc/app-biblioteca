package com.pe.crce.biblioteca.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.pe.crce.biblioteca.constant.BibliotecaConstant;
import com.pe.crce.biblioteca.dto.AuthorDTO;
import com.pe.crce.biblioteca.model.Author;
import com.pe.crce.biblioteca.repository.AuthorRepository;
import com.pe.crce.biblioteca.service.AuthorService;

@Service
public class AuthorServiceImpl implements AuthorService{

	final AuthorRepository authorRepository;

	public AuthorServiceImpl(AuthorRepository authorRepository) {
		this.authorRepository = authorRepository;
	}

	@Override
	public List<AuthorDTO> findByKeyWordSQL(String key_word) {
		List<Author> list = this.authorRepository.findByKeyWordSQL(key_word, BibliotecaConstant.STATE_ACTIVE);
		return list.stream()
				.map((bean)->convertBeanToDto(bean))
				.collect(Collectors.toList());
	}
	
	public AuthorDTO convertBeanToDto(Author author) {
		return AuthorDTO.builder()
				.id(author.getId())
				.authorName(author.getName() + " "+author.getLastName())
				.build();
	}
	
	
}
