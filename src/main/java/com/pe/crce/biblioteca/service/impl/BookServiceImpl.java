package com.pe.crce.biblioteca.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.pe.crce.biblioteca.constant.BibliotecaConstant;
import com.pe.crce.biblioteca.constant.GetReportColumnsConstant;
import com.pe.crce.biblioteca.dto.BookDTO;
import com.pe.crce.biblioteca.dto.HrefEntityDTO;
import com.pe.crce.biblioteca.dto.request.BookDTORequest;
import com.pe.crce.biblioteca.errorhandler.EntityNotFoundException;
import com.pe.crce.biblioteca.export.ResourceExport;
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
	
	final
	ResourceExport resourceExport;

	public BookServiceImpl(BookRepository bookRepository, BookMapper bookMapper, BibliotecaUtil util,
			EditorialRespository editorialRespository,SubAreaRepository subAreaRepository, ResourceExport resourceExport) {
		this.bookRepository = bookRepository;
		this.bookMapper = bookMapper;
		this.util = util;
		this.editorialRespository = editorialRespository;
		this.subAreaRepository = subAreaRepository;
		this.resourceExport = resourceExport;
	}

	@Override
	public HrefEntityDTO save(BookDTORequest dto) {
		Editorial editorial = this.editorialRespository.findByIdAndState(dto.getIdEditorial(), BibliotecaConstant.STATE_ACTIVE)
				.orElseThrow(()-> new EntityNotFoundException("not found editorial"));
		SubArea subArea = this.subAreaRepository.findByIdAndState(dto.getIdSubArea(), BibliotecaConstant.STATE_ACTIVE)
				.orElseThrow(()-> new EntityNotFoundException("not found subarea"));
		Book book = Book.builder()
				.title(dto.getTitle())
				.subtitle(dto.getSubTitle())
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
		book.setSubtitle(dto.getSubTitle());
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

	@Override
	public Page<BookDTO> findByKeyWordJPQL(String key_word, Pageable pageable) {
		Page<Book> books = this.bookRepository.findByKeyWordJPQL(key_word, BibliotecaConstant.STATE_ACTIVE, pageable);
		return books.map((b)-> this.bookMapper.toDto(b));
	}
	@Override
	public Boolean existsByIsbn(String isbn) {
		return this.bookRepository.existsByIsbnAndState(isbn, BibliotecaConstant.STATE_ACTIVE);
	}

	@Override
	public File generateExcel(List<BookDTO> books) {
		List<String> sheets = List.of(BibliotecaConstant.SHEET_BOOK);
		
		Map<String, List<String>> colsBySheet = new HashMap<>();
		List<String> cols = List.of(GetReportColumnsConstant.COL_BOOK_TITULO,GetReportColumnsConstant.COL_BOOK_EDITORIAL);
		
		colsBySheet.put(BibliotecaConstant.SHEET_BOOK, cols);
		
		Map<String, List<Map<String, String>>> valuesBySheet = new HashMap<>();
		List<Map<String, String>> valoresHoja = new ArrayList<>();
		
		books.forEach(row -> {
			Map<String, String> valuesHojaRow = new HashMap<>();
			valuesHojaRow.put(GetReportColumnsConstant.COL_BOOK_TITULO, row.getTitle().toLowerCase());
			valuesHojaRow.put(GetReportColumnsConstant.COL_BOOK_EDITORIAL, row.getEditorial().getName().toLowerCase());
			valoresHoja.add(valuesHojaRow);
		});
		valuesBySheet.put(BibliotecaConstant.SHEET_BOOK, valoresHoja);
		return this.resourceExport.generateExcel(sheets, colsBySheet, valuesBySheet, BibliotecaConstant.REPORT_NAME_BOOK_PAGINABLE);
	}
}
