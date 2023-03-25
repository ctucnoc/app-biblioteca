package com.pe.crce.biblioteca.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pe.crce.biblioteca.constant.BibliotecaConstant;
import com.pe.crce.biblioteca.dto.BookDTO;
import com.pe.crce.biblioteca.dto.HrefEntityDTO;
import com.pe.crce.biblioteca.dto.request.BookDTORequest;
import com.pe.crce.biblioteca.errorhandler.EntityNotFoundException;
import com.pe.crce.biblioteca.mapper.BookMapper;
import com.pe.crce.biblioteca.model.Book;
import com.pe.crce.biblioteca.model.Editorial;
import com.pe.crce.biblioteca.model.SubArea;
import com.pe.crce.biblioteca.repository.BookRepository;
import com.pe.crce.biblioteca.repository.EditorialRespository;
import com.pe.crce.biblioteca.repository.SubAreaRepository;
import com.pe.crce.biblioteca.service.BookService;
import com.pe.crce.biblioteca.util.BibliotecaResource;
import com.pe.crce.biblioteca.util.BibliotecaUtil;

@Service
@Transactional
public class BookServiceImpl implements BookService{

	final
	BookRepository bookRepository;
	
	final
	BookMapper bookMapper;
	
	final
	
	BibliotecaUtil util;
	
	final
	EditorialRespository editorialRespository;
	
	final
	SubAreaRepository subAreaRepository;

	public BookServiceImpl(BookRepository bookRepository, BookMapper bookMapper, BibliotecaUtil util,
			EditorialRespository editorialRespository,SubAreaRepository subAreaRepository) {
		this.bookRepository = bookRepository;
		this.bookMapper = bookMapper;
		this.util = util;
		this.editorialRespository = editorialRespository;
		this.subAreaRepository = subAreaRepository;
	}

	@Override
	public HrefEntityDTO save(BookDTORequest dto) {
		Editorial editorial = this.editorialRespository.findByIdAndState(dto.getIdEditorial(), BibliotecaConstant.STATE_ACTIVE)
				.orElseThrow(()-> new EntityNotFoundException("not found editorial"));
		SubArea subArea = this.subAreaRepository.findByIdAndState(dto.getIdEditorial(), BibliotecaConstant.STATE_ACTIVE)
				.orElseThrow(()-> new EntityNotFoundException("not found subarea"));
		Book book = Book.builder()
				.title(dto.getTitle())
				.subtitle(dto.getSubtitle())
				.isbn(dto.getIsbn())
				.description(dto.getDescription())
				.numberPage(dto.getNumberPage())
				.yearPublication(dto.getYearPublication())
				.editorial(editorial)
				.subArea(subArea).build();
		return util.createHrefFromResource(this.bookRepository.save(book).getId(), BibliotecaResource.BOOK);
	}

	@Override
	public HrefEntityDTO update(BookDTORequest dto, Long id) {
		Editorial editorial = this.editorialRespository.findByIdAndState(dto.getIdEditorial(), BibliotecaConstant.STATE_ACTIVE)
				.orElseThrow(()-> new EntityNotFoundException("not found editorial"));
		SubArea subArea = this.subAreaRepository.findByIdAndState(dto.getIdSubArea(), BibliotecaConstant.STATE_ACTIVE)
				.orElseThrow(()-> new EntityNotFoundException("not found subarea"));
		Book book = this.bookRepository.findByIdAndState(id, BibliotecaConstant.STATE_ACTIVE)
				.orElseThrow(()-> new EntityNotFoundException("not found book"));
		book.setTitle(dto.getTitle());
		book.setSubtitle(dto.getSubtitle());
		book.setDescription(dto.getDescription());
		book.setNumberPage(dto.getNumberPage());
		book.setYearPublication(dto.getYearPublication());
		book.setEditorial(editorial);
		book.setSubArea(subArea);
		return util.createHrefFromResource(this.bookRepository.save(book).getId(), BibliotecaResource.BOOK);
	}

	@Override
	public HrefEntityDTO delete(Long id) {
		Book book = this.bookRepository.findByIdAndState(id, BibliotecaConstant.STATE_ACTIVE)
				.orElseThrow(()-> new EntityNotFoundException("not found book"));
		book.setState(BibliotecaConstant.STATE_INACTIVE);
		return util.createHrefFromResource(this.bookRepository.save(book).getId(), BibliotecaResource.BOOK);
	}

	@Override
	public BookDTO findById(Long id) {
		Book book = this.bookRepository.findByIdAndState(id, BibliotecaConstant.STATE_ACTIVE)
				.orElseThrow(()-> new EntityNotFoundException("not found book"));
		return this.bookMapper.toDto(book);
	}
	
	
}
