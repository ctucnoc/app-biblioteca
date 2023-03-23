package com.pe.crce.biblioteca.mapper;

import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.pe.crce.biblioteca.dto.SubAreaDTO;
import com.pe.crce.biblioteca.model.SubArea;

@Mapper(builder = @Builder(disableBuilder = true))
public interface SubAreaMapper {

	@Mapping(target = "area.description", source = "area.description")
	@Mapping(target = "area.id", source = "area.id")
	public SubAreaDTO toDto(SubArea subArea);
}
