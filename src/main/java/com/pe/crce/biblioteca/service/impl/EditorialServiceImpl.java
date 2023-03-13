package com.pe.crce.biblioteca.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.pe.crce.biblioteca.constant.BibliotecaConstant;
import com.pe.crce.biblioteca.dto.EditorialDTO;
import com.pe.crce.biblioteca.dto.request.EditorialDTORequest;
import com.pe.crce.biblioteca.mapper.EditorialMapper;
import com.pe.crce.biblioteca.model.Editorial;
import com.pe.crce.biblioteca.repository.EditorialRespository;
import com.pe.crce.biblioteca.service.EditorialService;

@Service
public class EditorialServiceImpl implements EditorialService {

	final
	EditorialRespository editorialRespository;
	
	final
	EditorialMapper editorialMapper;

	public EditorialServiceImpl(EditorialRespository editorialRespository,EditorialMapper editorialMapper) {
		this.editorialRespository = editorialRespository;
		this.editorialMapper = editorialMapper;
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

	@Override
	public Page<EditorialDTO> findByNameLike(String name,Pageable pageable) {
		Page<Editorial> editorialPages = this.editorialRespository.findByNameLikeAndState("%"+name+"%", BibliotecaConstant.STATE_ACTIVE,pageable);
		return editorialPages
				.map((bean)->editorialMapper.toDto(bean));		
	}
	
	public EditorialDTO convertBeanToDto(Editorial editorial) {
		return EditorialDTO.builder()
				.id(editorial.getId())
				.name(editorial.getName())
				.build();
	}

}
