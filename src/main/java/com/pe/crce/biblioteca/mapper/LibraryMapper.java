package com.pe.crce.biblioteca.mapper;

import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import com.pe.crce.biblioteca.dto.LibraryDTO;
import com.pe.crce.biblioteca.dto.request.LibraryDTORequest;
import com.pe.crce.biblioteca.model.Library;

@Mapper(builder = @Builder(disableBuilder = false))
public interface LibraryMapper {

	public LibraryDTO toDto(Library library);
	
	public Library toBean(LibraryDTORequest dto);
}
