package com.pe.crce.biblioteca.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.util.ArrayIterator;
import com.pe.crce.biblioteca.constant.BibliotecaConstant;
import com.pe.crce.biblioteca.dto.EditorialDTO;
import com.pe.crce.biblioteca.dto.request.EditorialDTORequest;
import com.pe.crce.biblioteca.model.Editorial;
import com.pe.crce.biblioteca.repository.EditorialRespository;
import com.pe.crce.biblioteca.service.EditorialService;

@Service
public class EditorialServiceImpl implements EditorialService {

	EditorialRespository editorialRespository;

	public EditorialServiceImpl(EditorialRespository editorialRespository) {
		this.editorialRespository = editorialRespository;
	}

	@Override
	public Long save(EditorialDTORequest dto) {
		Editorial editorial = new Editorial();
		editorial.setName(dto.getName());
		editorial.setState(BibliotecaConstant.STATE_ACTIVE);
		return this.editorialRespository.save(editorial).getId();
	}

	@Override
	public EditorialDTO findById(Long id) {
		Editorial editorial = this.editorialRespository.findById(id).get();
		EditorialDTO dto = new EditorialDTO();
		dto.setId(editorial.getId());
		dto.setName(editorial.getName());
		return dto;
	}

	@Override
	public List<EditorialDTO> findaLL() {
		List<Editorial> list = this.editorialRespository.findAll();
		List<EditorialDTO> listDtos = new ArrayList<>();
		list.forEach((bean) -> {
			EditorialDTO dto = new EditorialDTO();
			dto.setId(bean.getId());
			dto.setName(bean.getName());
			listDtos.add(dto);
		});
		return listDtos;
	}

	@Override
	public Long update(EditorialDTORequest dto, Long id) {
		Editorial editorial = this.editorialRespository.findById(id).get();
		editorial.setName(dto.getName());
		return this.editorialRespository.save(editorial).getId();
	}

}
