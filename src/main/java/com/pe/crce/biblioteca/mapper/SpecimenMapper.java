package com.pe.crce.biblioteca.mapper;

import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import com.pe.crce.biblioteca.dto.SpecimenDTO;
import com.pe.crce.biblioteca.model.Specimen;

@Mapper(builder = @Builder(disableBuilder = true))
public interface SpecimenMapper {

	public SpecimenDTO toDto(Specimen specimen);
	
	public Specimen toBean(SpecimenDTO dto);
}
