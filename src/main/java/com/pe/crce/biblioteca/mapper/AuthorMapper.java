package com.pe.crce.biblioteca.mapper;

import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.pe.crce.biblioteca.dto.AuthorDTO;
import com.pe.crce.biblioteca.model.Author;

@Mapper(builder = @Builder(disableBuilder = true))
public interface AuthorMapper {

	@Mapping(target = "authorName",expression = "java(author.getName().concat(\" \").concat(author.getLastName()))")
	public AuthorDTO toDto(Author author);
}
