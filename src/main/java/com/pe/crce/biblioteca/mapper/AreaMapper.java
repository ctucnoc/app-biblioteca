package com.pe.crce.biblioteca.mapper;

import org.mapstruct.Builder;
import org.mapstruct.Mapper;

import com.pe.crce.biblioteca.dto.AreaDTO;
import com.pe.crce.biblioteca.dto.request.AreaDTORequest;
import com.pe.crce.biblioteca.model.Area;

@Mapper(builder = @Builder(disableBuilder = true))
public interface AreaMapper {

	public Area toBean(AreaDTORequest dto);
	
	public AreaDTO toDto(Area area);
}
