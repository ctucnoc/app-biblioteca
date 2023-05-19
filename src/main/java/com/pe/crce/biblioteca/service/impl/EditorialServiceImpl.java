package com.pe.crce.biblioteca.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.pe.crce.biblioteca.constant.BibliotecaConstant;
import com.pe.crce.biblioteca.dto.EditorialDTO;
import com.pe.crce.biblioteca.dto.HrefEntityDTO;
import com.pe.crce.biblioteca.dto.request.EditorialDTORequest;
import com.pe.crce.biblioteca.errorhandler.EntityNotFoundException;
import com.pe.crce.biblioteca.mapper.EditorialMapper;
import com.pe.crce.biblioteca.model.Editorial;
import com.pe.crce.biblioteca.repository.EditorialRespository;
import com.pe.crce.biblioteca.service.EditorialService;
import com.pe.crce.biblioteca.util.BibliotecaResource;
import com.pe.crce.biblioteca.util.BibliotecaUtil;

@Transactional
@Service
public class EditorialServiceImpl implements EditorialService {

	final EditorialRespository editorialRespository;

	final EditorialMapper editorialMapper;

	final BibliotecaUtil util;

	public EditorialServiceImpl(EditorialRespository editorialRespository, EditorialMapper editorialMapper,
			BibliotecaUtil util) {
		this.editorialRespository = editorialRespository;
		this.editorialMapper = editorialMapper;
		this.util = util;
	}

	@Override
	public HrefEntityDTO save(EditorialDTORequest dto) {
		Editorial editorial = new Editorial();
		editorial.setName(dto.getName());
		editorial.setState(BibliotecaConstant.STATE_ACTIVE);
		return util.createHrefFromResource(this.editorialRespository.save(editorial).getId(),
				BibliotecaResource.EDITORIAL);
	}

	@Override
	public EditorialDTO findById(Long id) {
		Editorial editorial = this.editorialRespository.findById(id).orElseThrow(
				() -> new EntityNotFoundException(String.format("La editorial con id %s no existe", id.toString())));
		return editorialMapper.toDto(editorial);
	}

	@Override
	public List<EditorialDTO> findaLL() {
		List<Editorial> list = this.editorialRespository.findAll();
		return list.stream().map((bean) -> editorialMapper.toDto(bean)).collect(Collectors.toList());
	}

	@Override
	public HrefEntityDTO update(EditorialDTORequest dto, Long id) {
		Editorial editorial = this.editorialRespository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("not found editorial"));
		editorial.setName(dto.getName());
		return util.createHrefFromResource(this.editorialRespository.save(editorial).getId(),
				BibliotecaResource.EDITORIAL);
	}

	@Override
	public Page<EditorialDTO> findByNameLike(String name, Pageable pageable) {
		Page<Editorial> editorialPages = this.editorialRespository.findByNameContainingIgnoreCaseAndState(BibliotecaUtil.preFormatCadena(name),
				BibliotecaConstant.STATE_ACTIVE, pageable);
		return editorialPages.map((bean) -> editorialMapper.toDto(bean));
	}

	@Override
	public HrefEntityDTO delete(Long id) {
		Editorial editorial = this.editorialRespository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("not found editorial"));
		editorial.setState(BibliotecaConstant.STATE_INACTIVE);
		return util.createHrefFromResource(this.editorialRespository.save(editorial).getId(),
				BibliotecaResource.EDITORIAL);
	}

}
