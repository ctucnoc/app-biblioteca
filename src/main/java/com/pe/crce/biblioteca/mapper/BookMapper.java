package com.pe.crce.biblioteca.mapper;

import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import com.pe.crce.biblioteca.dto.BookDTO;
import com.pe.crce.biblioteca.dto.request.BookDTORequest;
import com.pe.crce.biblioteca.model.Book;

@Mapper(builder = @Builder(disableBuilder = true))
public interface BookMapper {

	public BookDTO toDto(Book book);
	
	public Book toBean(BookDTORequest dto);
}
