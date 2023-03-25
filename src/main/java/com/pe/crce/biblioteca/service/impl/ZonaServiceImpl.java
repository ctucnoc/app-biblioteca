package com.pe.crce.biblioteca.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pe.crce.biblioteca.constant.BibliotecaConstant;
import com.pe.crce.biblioteca.dto.HrefEntityDTO;
import com.pe.crce.biblioteca.dto.ZoneDTO;
import com.pe.crce.biblioteca.dto.request.ZoneDTORequest;
import com.pe.crce.biblioteca.errorhandler.EntityNotFoundException;
import com.pe.crce.biblioteca.mapper.ZoneMapper;
import com.pe.crce.biblioteca.model.Library;
import com.pe.crce.biblioteca.model.Zone;
import com.pe.crce.biblioteca.repository.LibraryRepositorio;
import com.pe.crce.biblioteca.repository.ZonaRepository;
import com.pe.crce.biblioteca.service.ZonaService;
import com.pe.crce.biblioteca.util.BibliotecaResource;
import com.pe.crce.biblioteca.util.BibliotecaUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ZonaServiceImpl implements ZonaService{

	final
	ZonaRepository zonaRepository;
	
	final
	BibliotecaUtil util;
	
	final
	ZoneMapper zoneMapper;
	
	final
	LibraryRepositorio libraryRepositorio;

	public ZonaServiceImpl(ZonaRepository zonaRepository, BibliotecaUtil util,ZoneMapper zoneMapper,LibraryRepositorio libraryRepositorio) {
		this.zonaRepository = zonaRepository;
		this.util = util;
		this.zoneMapper = zoneMapper;
		this.libraryRepositorio = libraryRepositorio;
	}

	@Override
	public HrefEntityDTO save(ZoneDTORequest dto) {
		Library library = this.libraryRepositorio.findByIdAndState(dto.getIdLibrary(), BibliotecaConstant.STATE_ACTIVE)
				.orElseThrow(()-> new EntityNotFoundException("not found library"));
		Zone zone = Zone.builder()
				.description(dto.getDescription())
				.library(library).build();
		return this.util.createHrefFromResource(this.zonaRepository.save(zone).getId(), BibliotecaResource.ZONE);
	}

	@Override
	public HrefEntityDTO update(ZoneDTORequest dto, Long id) {
		Library library = this.libraryRepositorio.findByIdAndState(dto.getIdLibrary(), BibliotecaConstant.STATE_ACTIVE)
				.orElseThrow(()-> new EntityNotFoundException("not found library"));
		Zone zone = this.zonaRepository.findByIdAndState(id, BibliotecaConstant.STATE_ACTIVE)
				.orElseThrow(()-> new EntityNotFoundException("not found zona"));
		zone.setDescription(dto.getDescription());
		zone.setLibrary(library);
		return this.util.createHrefFromResource(this.zonaRepository.save(zone).getId(), BibliotecaResource.ZONE);
	}

	@Override
	public HrefEntityDTO delete(Long id) {
		Zone zone = this.zonaRepository.findByIdAndState(id, BibliotecaConstant.STATE_ACTIVE)
				.orElseThrow(()-> new EntityNotFoundException("not found zona"));
		zone.setState(BibliotecaConstant.STATE_INACTIVE);
		return this.util.createHrefFromResource(this.zonaRepository.save(zone).getId(), BibliotecaResource.ZONE);
	}

	@Override
	public Page<ZoneDTO> findByDescription(String description, Pageable pageable) {
		Page<Zone> zones = this.zonaRepository.findByDescriptionContainingAndState(description, BibliotecaConstant.STATE_ACTIVE, pageable);
		return zones.map((zona)-> this.zoneMapper.toDto(zona));
	}

	@Override
	public ZoneDTO findById(Long id) {
		Zone zone = this.zonaRepository.findByIdAndState(id, BibliotecaConstant.STATE_ACTIVE)
				.orElseThrow(()-> new EntityNotFoundException("not found zona"));
		log.info("crce service findById -> {} " +zone.toString());
		return this.zoneMapper.toDto(zone);
	}
	
	
}
