package com.pe.crce.biblioteca.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pe.crce.biblioteca.constant.BibliotecaConstant;
import com.pe.crce.biblioteca.dto.HrefEntityDTO;
import com.pe.crce.biblioteca.dto.LibraryDTO;
import com.pe.crce.biblioteca.dto.request.LibraryDTORequest;
import com.pe.crce.biblioteca.errorhandler.EntityNotFoundException;
import com.pe.crce.biblioteca.mapper.LibraryMapper;
import com.pe.crce.biblioteca.model.Library;
import com.pe.crce.biblioteca.repository.LibraryRepositorio;
import com.pe.crce.biblioteca.service.LibraryService;
import com.pe.crce.biblioteca.util.BibliotecaResource;
import com.pe.crce.biblioteca.util.BibliotecaUtil;

@Service
@Transactional
public class LibraryServiceImpl implements LibraryService {
	
	final
	LibraryRepositorio libraryRepositorio;
	
	final
	LibraryMapper libraryMapper;
	
	final
	BibliotecaUtil util;
	
	public LibraryServiceImpl(LibraryRepositorio libraryRepositorio,LibraryMapper libraryMapper,BibliotecaUtil util) {
		this.libraryRepositorio = libraryRepositorio;
		this.libraryMapper = libraryMapper;
		this.util = util;
	}

	@Override
	public Page<LibraryDTO> findByNameLikeJPA(String key_word, Pageable pageable) {
		Page<Library> libraries = this.libraryRepositorio.findByNameLikeJPA(key_word, BibliotecaConstant.STATE_ACTIVE, pageable);
		return libraries
				.map((bean)->libraryMapper.toDto(bean));
	}

	@Override
	public HrefEntityDTO save(LibraryDTORequest dto) {
		Library library = this.libraryRepositorio.save(this.libraryMapper.toBean(dto));
		return util.createHrefFromResource(library.getId(), BibliotecaResource.LIBRARY);
	}

	@Override
	public HrefEntityDTO update(LibraryDTORequest dto, Long id) {
		Library library = this.libraryRepositorio.findByIdAndState(id, BibliotecaConstant.STATE_ACTIVE)
				.orElseThrow(()-> new EntityNotFoundException("not found library"));
		library.setAddress(dto.getAddress());
		library.setDescription(dto.getDescription());
		library.setName(dto.getName());
		return util.createHrefFromResource(this.libraryRepositorio.save(library).getId(), BibliotecaResource.LIBRARY);
	}

	@Override
	public HrefEntityDTO delete(Long id) {
		Library library = this.libraryRepositorio.findByIdAndState(id, BibliotecaConstant.STATE_ACTIVE)
				.orElseThrow(()-> new EntityNotFoundException("not found library"));
		library.setState(BibliotecaConstant.STATE_INACTIVE);
		return util.createHrefFromResource(this.libraryRepositorio.save(library).getId(), BibliotecaResource.LIBRARY);
	}

	@Override
	public LibraryDTO findById(Long id) {
		Library library = this.libraryRepositorio.findByIdAndState(id, BibliotecaConstant.STATE_ACTIVE)
				.orElseThrow(()-> new EntityNotFoundException("not found library"));
		return this.libraryMapper.toDto(library);
	}

	@Override
	public List<LibraryDTO> findByKeyWordJPA(String key_word) {
		return this.libraryRepositorio.findByKeyWordLikeJPA(BibliotecaUtil.preFormatCadena(key_word), BibliotecaConstant.STATE_ACTIVE)
				.stream()
				.limit(15)
				.map((bean)-> libraryMapper.toDto(bean))
				.collect(Collectors.toList());
				
	}

}
