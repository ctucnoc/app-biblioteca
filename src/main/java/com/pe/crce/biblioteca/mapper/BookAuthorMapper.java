package com.pe.crce.biblioteca.mapper;

import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.pe.crce.biblioteca.dto.BookAuthorDTO;
import com.pe.crce.biblioteca.model.BookAuthor;

@Mapper(builder = @Builder(disableBuilder = true))
public interface BookAuthorMapper {

	@Mapping(target = "author.description", expression = "java(author.getName().concat(\" \").concat(author.getLastName()))")
	@Mapping(target = "author.id", source = "author.id")
	public BookAuthorDTO toDto(BookAuthor bookAuthor);
}
