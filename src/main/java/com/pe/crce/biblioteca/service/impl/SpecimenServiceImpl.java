package com.pe.crce.biblioteca.service.impl;

import java.util.Date;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.pe.crce.biblioteca.constant.BibliotecaConstant;
import com.pe.crce.biblioteca.dto.HrefEntityDTO;
import com.pe.crce.biblioteca.dto.SpecimenDTO;
import com.pe.crce.biblioteca.dto.request.SpecimenDTORequest;
import com.pe.crce.biblioteca.errorhandler.EntityNotFoundException;
import com.pe.crce.biblioteca.mapper.SpecimenMapper;
import com.pe.crce.biblioteca.model.Book;
import com.pe.crce.biblioteca.model.Specimen;
import com.pe.crce.biblioteca.model.SpecimenState;
import com.pe.crce.biblioteca.model.Zone;
import com.pe.crce.biblioteca.repository.BookRepository;
import com.pe.crce.biblioteca.repository.SpecimenRepository;
import com.pe.crce.biblioteca.repository.SpecimenStateRepository;
import com.pe.crce.biblioteca.repository.ZonaRepository;
import com.pe.crce.biblioteca.service.SpecimenService;
import com.pe.crce.biblioteca.util.BibliotecaResource;
import com.pe.crce.biblioteca.util.BibliotecaUtil;

@Service
@Transactional
public class SpecimenServiceImpl implements SpecimenService{

	final
	SpecimenRepository specimenRepository;
	
	final
	SpecimenMapper specimenMapper;
	
	final
	BibliotecaUtil util;
	
	final
	ZonaRepository zonaRepository;
	
	final
	BookRepository bookRepository;
	
	final
	SpecimenStateRepository specimenStateRepository;

	public SpecimenServiceImpl(SpecimenRepository specimenRepository, SpecimenMapper specimenMapper,
			BibliotecaUtil util, ZonaRepository zonaRepository, BookRepository bookRepository,
			SpecimenStateRepository specimenStateRepository) {
		this.specimenRepository = specimenRepository;
		this.specimenMapper = specimenMapper;
		this.util = util;
		this.zonaRepository = zonaRepository;
		this.bookRepository = bookRepository;
		this.specimenStateRepository = specimenStateRepository;
	}

	@Override
	public HrefEntityDTO save(SpecimenDTORequest dto) {
		Zone zone = this.zonaRepository.findByIdAndState(dto.getIdZone(), BibliotecaConstant.STATE_ACTIVE)
				.orElseThrow(()-> new EntityNotFoundException("not found zone"));
		Book book = this.bookRepository.findByIdAndState(dto.getIdZone(), BibliotecaConstant.STATE_ACTIVE)
				.orElseThrow(()-> new EntityNotFoundException("not found book"));
		SpecimenState specimenState = this.specimenStateRepository.findByIdAndState(dto.getIdZone(), BibliotecaConstant.STATE_ACTIVE)
				.orElseThrow(()-> new EntityNotFoundException("not found specimen state"));
		Specimen specimen = Specimen.builder()
				.code(dto.getCode())
				.quantity(dto.getQuantity())
				.dateRegister(new Date())
				.book(book)
				.specimenState(specimenState)
				.zone(zone).build();
		return this.util.createHrefFromResource(this.specimenRepository.save(specimen).getId(), BibliotecaResource.SPECIMEN);
	}

	@Override
	public HrefEntityDTO update(SpecimenDTORequest dto, Long id) {
		Zone zone = this.zonaRepository.findByIdAndState(dto.getIdZone(), BibliotecaConstant.STATE_ACTIVE)
				.orElseThrow(()-> new EntityNotFoundException("not found zone"));
		Book book = this.bookRepository.findByIdAndState(dto.getIdZone(), BibliotecaConstant.STATE_ACTIVE)
				.orElseThrow(()-> new EntityNotFoundException("not found book"));
		SpecimenState specimenState = this.specimenStateRepository.findByIdAndState(dto.getIdZone(), BibliotecaConstant.STATE_ACTIVE)
				.orElseThrow(()-> new EntityNotFoundException("not found specimen state"));
		Specimen specimen = this.specimenRepository.findByIdAndState(id, BibliotecaConstant.STATE_ACTIVE)
				.orElseThrow(()-> new EntityNotFoundException("not found specimen"));
		specimen.setBook(book);
		specimen.setSpecimenState(specimenState);
		specimen.setZone(zone);
		specimen.setCode(dto.getCode());
		specimen.setQuantity(dto.getQuantity());
		return this.util.createHrefFromResource(this.specimenRepository.save(specimen).getId(), BibliotecaResource.SPECIMEN);
	}

	@Override
	public HrefEntityDTO delete(Long id) {
		Specimen specimen = this.specimenRepository.findByIdAndState(id, BibliotecaConstant.STATE_ACTIVE)
				.orElseThrow(()-> new EntityNotFoundException("not found specimen"));
		specimen.setState(BibliotecaConstant.STATE_INACTIVE);
		return this.util.createHrefFromResource(this.specimenRepository.save(specimen).getId(), BibliotecaResource.SPECIMEN);
	}

	@Override
	public Page<SpecimenDTO> findByKeyWordJPQL(String key_word, Pageable pageable) {
		Page<Specimen> specimens = this.specimenRepository.findByKeyWordAndStateJPQL(BibliotecaUtil.preFormatCadena(key_word), BibliotecaConstant.STATE_ACTIVE, pageable);
		return specimens.map((specimen)-> this.specimenMapper.toDto(specimen));
	}

	@Override
	public SpecimenDTO findById(Long id) {
		Specimen specimen = this.specimenRepository.findByIdAndState(id, BibliotecaConstant.STATE_ACTIVE)
				.orElseThrow(()-> new EntityNotFoundException("not found specimen"));
		return this.specimenMapper.toDto(specimen);
	}	
	
}
