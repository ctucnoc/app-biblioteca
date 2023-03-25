package com.pe.crce.biblioteca.mapper;

import org.mapstruct.Builder;
import org.mapstruct.Mapper;

import com.pe.crce.biblioteca.dto.ZoneDTO;
import com.pe.crce.biblioteca.dto.request.ZoneDTORequest;
import com.pe.crce.biblioteca.model.Zone;

@Mapper(builder = @Builder(disableBuilder = true))
public interface ZoneMapper {

	public ZoneDTO toDto(Zone zone);
	
	public Zone toBean(ZoneDTORequest dto);
}
