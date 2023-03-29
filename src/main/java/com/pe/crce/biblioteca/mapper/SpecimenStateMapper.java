package com.pe.crce.biblioteca.mapper;

import org.mapstruct.Builder;
import org.mapstruct.Mapper;

import com.pe.crce.biblioteca.dto.SpecimenStateDTO;
import com.pe.crce.biblioteca.dto.request.SpecimenStateDTORequest;
import com.pe.crce.biblioteca.model.SpecimenState;

@Mapper(builder = @Builder(disableBuilder = true))
public interface SpecimenStateMapper {

	public SpecimenStateDTO toDto(SpecimenState specimenState);
	
	public SpecimenState toBean(SpecimenStateDTORequest dto);
}
