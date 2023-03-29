package com.pe.crce.biblioteca.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.pe.crce.biblioteca.constant.BibliotecaConstant;
import com.pe.crce.biblioteca.dto.HrefEntityDTO;
import com.pe.crce.biblioteca.dto.SpecimenStateDTO;
import com.pe.crce.biblioteca.dto.request.SpecimenStateDTORequest;
import com.pe.crce.biblioteca.errorhandler.EntityNotFoundException;
import com.pe.crce.biblioteca.mapper.SpecimenStateMapper;
import com.pe.crce.biblioteca.model.SpecimenState;
import com.pe.crce.biblioteca.repository.SpecimenStateRepository;
import com.pe.crce.biblioteca.service.SpecimenStateService;
import com.pe.crce.biblioteca.util.BibliotecaResource;
import com.pe.crce.biblioteca.util.BibliotecaUtil;

@Service
@Transactional
public class SpecimenStateServiceImpl implements SpecimenStateService{

	final
	SpecimenStateRepository specimenStateRepository;
	
	final
	SpecimenStateMapper specimenStateMapper;
	
	final
	BibliotecaUtil util;

	public SpecimenStateServiceImpl(SpecimenStateRepository specimenStateRepository,
			SpecimenStateMapper specimenStateMapper, BibliotecaUtil util) {
		this.specimenStateRepository = specimenStateRepository;
		this.specimenStateMapper = specimenStateMapper;
		this.util = util;
	}

	@Override
	public HrefEntityDTO save(SpecimenStateDTORequest dto) {
		SpecimenState specimenState = this.specimenStateRepository.save(this.specimenStateMapper.toBean(dto));
		return this.util.createHrefFromResource(specimenState.getId(), BibliotecaResource.SPECIMENSTATE);
	}

	@Override
	public HrefEntityDTO update(SpecimenStateDTORequest dto, Long id) {
		SpecimenState specimenState = this.specimenStateRepository.findByIdAndState(id, BibliotecaConstant.STATE_ACTIVE)
				.orElseThrow(()-> new EntityNotFoundException("not found specimen state"));
		specimenState.setDescription(dto.getDescription());
		return this.util.createHrefFromResource(this.specimenStateRepository.save(specimenState).getId(), BibliotecaResource.SPECIMENSTATE);
	}

	@Override
	public HrefEntityDTO delete(Long id) {
		SpecimenState specimenState = this.specimenStateRepository.findByIdAndState(id, BibliotecaConstant.STATE_ACTIVE)
				.orElseThrow(()-> new EntityNotFoundException("not found specimen state"));
		specimenState.setState(BibliotecaConstant.STATE_INACTIVE);
		return this.util.createHrefFromResource(this.specimenStateRepository.save(specimenState).getId(), BibliotecaResource.SPECIMENSTATE);
	}

	@Override
	public SpecimenStateDTO findById(Long id) {
		SpecimenState specimenState = this.specimenStateRepository.findByIdAndState(id, BibliotecaConstant.STATE_ACTIVE)
				.orElseThrow(()-> new EntityNotFoundException("not found specimen state"));
		return this.specimenStateMapper.toDto(specimenState);
	}

	@Override
	public Page<SpecimenStateDTO> findByDescription(String description, Pageable pageable) {
		Page<SpecimenState> specimenstates = this.specimenStateRepository.findByDescriptionContainingAndState(description, BibliotecaConstant.STATE_ACTIVE, pageable);
		return specimenstates.map((bean)-> this.specimenStateMapper.toDto(bean));
	}
	
	
}
