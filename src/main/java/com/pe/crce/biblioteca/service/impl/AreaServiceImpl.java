package com.pe.crce.biblioteca.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pe.crce.biblioteca.constant.BibliotecaConstant;
import com.pe.crce.biblioteca.dto.AreaDTO;
import com.pe.crce.biblioteca.dto.HrefEntityDTO;
import com.pe.crce.biblioteca.dto.request.AreaDTORequest;
import com.pe.crce.biblioteca.errorhandler.EntityNotFoundException;
import com.pe.crce.biblioteca.mapper.AreaMapper;
import com.pe.crce.biblioteca.model.Area;
import com.pe.crce.biblioteca.repository.AreaRepository;
import com.pe.crce.biblioteca.service.AreaService;
import com.pe.crce.biblioteca.util.BibliotecaResource;
import com.pe.crce.biblioteca.util.BibliotecaUtil;

@Transactional
@Service
public class AreaServiceImpl implements AreaService{

	final
	AreaRepository areaRepository;
	
	final
	BibliotecaUtil util;
	
	final
	AreaMapper areaMapper;

	public AreaServiceImpl(AreaRepository areaRepository, BibliotecaUtil util, AreaMapper areaMapper) {
		this.areaRepository = areaRepository;
		this.util = util;
		this.areaMapper = areaMapper;
	}

	@Override
	public HrefEntityDTO save(AreaDTORequest dto) {
		Area area = this.areaRepository.save(this.areaMapper.toBean(dto));
		return this.util.createHrefFromResource(area.getId(), BibliotecaResource.AREA);
	}

	@Override
	public HrefEntityDTO update(AreaDTORequest dto, Long id) {
		Area area = this.areaRepository.findByIdAndState(id, BibliotecaConstant.STATE_ACTIVE)
				.orElseThrow(()-> new EntityNotFoundException("not found"));
		area.setDescription(dto.getDescription());
		return this.util.createHrefFromResource(this.areaRepository.save(area).getId(), BibliotecaResource.AREA);
	}

	@Override
	public HrefEntityDTO delete(Long id) {
		Area area = this.areaRepository.findByIdAndState(id, BibliotecaConstant.STATE_ACTIVE)
				.orElseThrow(()-> new EntityNotFoundException("not found"));
		area.setState(BibliotecaConstant.STATE_INACTIVE);
		return this.util.createHrefFromResource(this.areaRepository.save(area).getId(), BibliotecaResource.AREA);
	}

	@Transactional(readOnly = true)
	@Override
	public Page<AreaDTO> findByDescription(String description, Pageable pageable) {
		Page<Area> areas = this.areaRepository.findByDescriptionContainingAndState(description, BibliotecaConstant.STATE_ACTIVE,pageable);
		return areas.map((bean)-> this.areaMapper.toDto(bean));
	}

	@Transactional(readOnly = true)
	@Override
	public AreaDTO findById(Long id) {
		Area area = this.areaRepository.findByIdAndState(id, BibliotecaConstant.STATE_ACTIVE)
				.orElseThrow(()-> new EntityNotFoundException("not found"));
		return this.areaMapper.toDto(area);
	}
	
}
