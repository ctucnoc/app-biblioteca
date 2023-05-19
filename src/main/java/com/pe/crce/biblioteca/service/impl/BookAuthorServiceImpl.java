package com.pe.crce.biblioteca.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.pe.crce.biblioteca.constant.BibliotecaConstant;
import com.pe.crce.biblioteca.dto.BookAuthorDTO;
import com.pe.crce.biblioteca.dto.HrefEntityDTO;
import com.pe.crce.biblioteca.dto.request.BookAuthorDTORequest;
import com.pe.crce.biblioteca.errorhandler.EntityNotFoundException;
import com.pe.crce.biblioteca.mapper.BookAuthorMapper;
import com.pe.crce.biblioteca.model.Author;
import com.pe.crce.biblioteca.model.Book;
import com.pe.crce.biblioteca.model.BookAuthor;
import com.pe.crce.biblioteca.repository.AuthorRepository;
import com.pe.crce.biblioteca.repository.BookAuthorRepository;
import com.pe.crce.biblioteca.repository.BookRepository;
import com.pe.crce.biblioteca.service.BookAuthorService;
import com.pe.crce.biblioteca.util.BibliotecaResource;
import com.pe.crce.biblioteca.util.BibliotecaUtil;

@Service
@Transactional
public class BookAuthorServiceImpl implements BookAuthorService{

	final
	BookAuthorRepository bookAuthorRepository;
	
	final
	BookAuthorMapper bookAuthorMapper;
	
	final
	BibliotecaUtil util;
	
	final 
	BookRepository bookRepository;
	
	final
	AuthorRepository authorRepository;

	public BookAuthorServiceImpl(BookAuthorRepository bookAuthorRepository, BookAuthorMapper bookAuthorMapper,
			BibliotecaUtil util,BookRepository bookRepository,AuthorRepository authorRepository) {
		this.bookAuthorRepository = bookAuthorRepository;
		this.bookAuthorMapper = bookAuthorMapper;
		this.util = util;
		this.bookRepository = bookRepository;
		this.authorRepository = authorRepository;
	}

	@Override
	public Page<BookAuthorDTO> findByBook(Long idBook, Pageable pageable) {
		Book book = this.bookRepository.findByIdAndState(idBook, BibliotecaConstant.STATE_ACTIVE)
				.orElseThrow(()-> new EntityNotFoundException("not found book"));
		Page<BookAuthor> bookAutors = this.bookAuthorRepository.findByBookAndState(book, BibliotecaConstant.STATE_ACTIVE, pageable);
		return bookAutors.map((bean)-> this.bookAuthorMapper.toDto(bean));
	}

	@Override
	public HrefEntityDTO save(BookAuthorDTORequest dto) {
		Book book = this.bookRepository.findByIdAndState(dto.getIdBook(), BibliotecaConstant.STATE_ACTIVE)
				.orElseThrow(()-> new EntityNotFoundException("not found book"));
		Author author = this.authorRepository.findByIdAndState(dto.getIdAuthor(), BibliotecaConstant.STATE_ACTIVE)
				.orElseThrow(()-> new EntityNotFoundException("not found author"));
		BookAuthor bookAuthor = BookAuthor.builder()
				.book(book)
				.author(author)
				.build();
		return this.util.createHrefFromResource(this.bookAuthorRepository.save(bookAuthor).getId(), BibliotecaResource.BOOKAUTHOR);
	}

	@Override
	public HrefEntityDTO delete(Long id) {
		BookAuthor bookAuthor = this.bookAuthorRepository.findByIdAndState(id, BibliotecaConstant.STATE_ACTIVE)
				.orElseThrow(()-> new EntityNotFoundException("not found boo author"));
		bookAuthor.setState(BibliotecaConstant.STATE_INACTIVE);
		return this.util.createHrefFromResource(this.bookAuthorRepository.save(bookAuthor).getId(), BibliotecaResource.BOOKAUTHOR);
	}	
}
