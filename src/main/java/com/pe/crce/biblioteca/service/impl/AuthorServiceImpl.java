package com.pe.crce.biblioteca.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.pe.crce.biblioteca.constant.BibliotecaConstant;
import com.pe.crce.biblioteca.dto.AuthorDTO;
import com.pe.crce.biblioteca.dto.HrefEntityDTO;
import com.pe.crce.biblioteca.dto.request.AuthorDTORequest;
import com.pe.crce.biblioteca.errorhandler.EntityNotFoundException;
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
	public Page<AuthorDTO> findByKeyWordSQL(String key_word,Pageable pageable) {
		Page<Author> list = this.authorRepository.findByKeyWordSQL(key_word, BibliotecaConstant.STATE_ACTIVE,pageable);
		return list
				.map((bean)->authorMapper.toDto(bean));
	}

	@Override
	public HrefEntityDTO saveSQL(AuthorDTORequest dto) {
		Author author = this.authorRepository.saveSQL(dto.getName(), dto.getLastName());
		return util.createHrefFromResource(author.getId(), BibliotecaResource.AUTHOR);
	}

	@Override
	public HrefEntityDTO update(AuthorDTORequest dto, Long id) {
		Author author = this.authorRepository.findByIdAndState(id, BibliotecaConstant.STATE_ACTIVE)
				.orElseThrow(()-> new EntityNotFoundException("not found author"));
		author.setName(dto.getName());
		author.setLastName(dto.getLastName());
		return util.createHrefFromResource(this.authorRepository.save(author).getId(), BibliotecaResource.AUTHOR);
	}

	@Override
	public HrefEntityDTO delete(Long id) {
		Author author = this.authorRepository.findByIdAndState(id, BibliotecaConstant.STATE_ACTIVE)
				.orElseThrow(()-> new EntityNotFoundException("not found author"));
		author.setName(BibliotecaConstant.STATE_INACTIVE);
		return util.createHrefFromResource(this.authorRepository.save(author).getId(), BibliotecaResource.AUTHOR);
	}

	@Override
	public AuthorDTO findById(Long id) {
		Author author = this.authorRepository.findByIdAndState(id, BibliotecaConstant.STATE_ACTIVE)
				.orElseThrow(()-> new EntityNotFoundException("not found author"));
		return this.authorMapper.toDto(author);
	}
	
}
