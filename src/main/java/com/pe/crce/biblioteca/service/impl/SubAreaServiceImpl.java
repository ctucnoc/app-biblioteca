package com.pe.crce.biblioteca.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.pe.crce.biblioteca.constant.BibliotecaConstant;
import com.pe.crce.biblioteca.dto.GenericDTO;
import com.pe.crce.biblioteca.dto.HrefEntityDTO;
import com.pe.crce.biblioteca.dto.SubAreaDTO;
import com.pe.crce.biblioteca.dto.request.SubAreaDTORequest;
import com.pe.crce.biblioteca.errorhandler.EntityNotFoundException;
import com.pe.crce.biblioteca.errorhandler.EntityUnprocessableException;
import com.pe.crce.biblioteca.mapper.SubAreaMapper;
import com.pe.crce.biblioteca.model.Area;
import com.pe.crce.biblioteca.model.SubArea;
import com.pe.crce.biblioteca.repository.AreaRepository;
import com.pe.crce.biblioteca.repository.SubAreaRepository;
import com.pe.crce.biblioteca.service.SubAreaService;
import com.pe.crce.biblioteca.util.BibliotecaResource;
import com.pe.crce.biblioteca.util.BibliotecaUtil;

@Service
@Transactional
public class SubAreaServiceImpl implements SubAreaService{

	final
	SubAreaRepository subAreaRepository;
	
	final 
	BibliotecaUtil util;
	
	final
	SubAreaMapper subAreaMapper;
	
	final
	AreaRepository areaRepository;

	public SubAreaServiceImpl(SubAreaRepository subAreaRepository, BibliotecaUtil util,SubAreaMapper subAreaMapper,AreaRepository areaRepository) {
		this.subAreaRepository = subAreaRepository;
		this.util = util;
		this.subAreaMapper = subAreaMapper;
		this.areaRepository = areaRepository;
	}

	@Override
	public SubAreaDTO findById(Long id) {
		SubArea subArea = this.subAreaRepository.findByIdAndState(id, BibliotecaConstant.STATE_ACTIVE)
				.orElseThrow(()-> new EntityNotFoundException("not found"));
		return this.subAreaMapper.toDto(subArea);
	}

	@Override
	public HrefEntityDTO save(SubAreaDTORequest dto) {
		Area area = this.areaRepository.findByIdAndState(dto.getIdArea(), BibliotecaConstant.STATE_ACTIVE)
				.orElseThrow(()-> new EntityNotFoundException("not found area"));
		if(this.subAreaRepository.existsByDescriptionAndAreaAndState(dto.getDescription(),area, BibliotecaConstant.STATE_ACTIVE)) {
			throw new EntityUnprocessableException(String.format("the area with id %s and description already exists", area.getId().toString()));
		}		
		SubArea subArea = SubArea.builder()
				.description(dto.getDescription())
				.area(area).build();
		return this.util.createHrefFromResource(this.subAreaRepository.save(subArea).getId(), BibliotecaResource.SUBAREA);
	}

	@Override
	public Page<SubAreaDTO> findByDescription(String description, Pageable pageable) {
		Page<SubArea> subareas = this.subAreaRepository.findByDescriptionContainingAndState(description, BibliotecaConstant.STATE_ACTIVE, pageable);		
		return subareas.map((subarea)-> this.subAreaMapper.toDto(subarea));
	}

	@Override
	public HrefEntityDTO update(SubAreaDTORequest dto, Long id) {
		Area area = this.areaRepository.findByIdAndState(dto.getIdArea(), BibliotecaConstant.STATE_ACTIVE)
				.orElseThrow(()-> new EntityNotFoundException("not found area"));
		SubArea subArea = this.subAreaRepository.findByIdAndState(id, BibliotecaConstant.STATE_ACTIVE)
				.orElseThrow(()-> new EntityNotFoundException("not found sub-area"));
		subArea.setDescription(dto.getDescription());
		subArea.setArea(area);
		return this.util.createHrefFromResource(this.subAreaRepository.save(subArea).getId(), BibliotecaResource.SUBAREA);
	}

	@Override
	public HrefEntityDTO delete(Long id) {
		SubArea subArea = this.subAreaRepository.findByIdAndState(id, BibliotecaConstant.STATE_ACTIVE)
				.orElseThrow(()-> new EntityNotFoundException("not found sub-area"));
		subArea.setState(BibliotecaConstant.STATE_INACTIVE);
		return this.util.createHrefFromResource(this.subAreaRepository.save(subArea).getId(), BibliotecaResource.SUBAREA);
	}

	@Override
	public Page<GenericDTO> findByAre(Long idArea, Pageable pageable) {
		Area area = this.areaRepository.findByIdAndState(idArea, BibliotecaConstant.STATE_ACTIVE)
				.orElseThrow(()-> new EntityNotFoundException("not found area"));
		return this.subAreaRepository.findByAreaAndState(area, BibliotecaConstant.STATE_ACTIVE, pageable)
				.map((bean)->this.convertGenericDTO(bean));
	}
	
	private GenericDTO convertGenericDTO(SubArea subArea) {
		return GenericDTO.builder()
				.id(subArea.getId())
				.description(subArea.getDescription())
				.build();
	}
}
