package com.pe.crce.biblioteca.mapper;

import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import com.pe.crce.biblioteca.dto.EditorialDTO;
import com.pe.crce.biblioteca.model.Editorial;

@Mapper(builder = @Builder(disableBuilder = false))
public interface EditorialMapper {

	EditorialDTO toDto(Editorial editorial);
}
