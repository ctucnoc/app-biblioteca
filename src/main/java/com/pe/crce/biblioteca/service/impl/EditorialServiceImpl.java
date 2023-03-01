package com.pe.crce.biblioteca.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pe.crce.biblioteca.model.Editorial;
import com.pe.crce.biblioteca.repository.EditorialRespository;
import com.pe.crce.biblioteca.service.EditorialService;

@Service
public class EditorialServiceImpl implements EditorialService{
	
	EditorialRespository editorialRespository;
	

	public EditorialServiceImpl(EditorialRespository editorialRespository) {
		this.editorialRespository = editorialRespository;
	}

	@Override
	public Editorial save(Editorial editorial) {
		return this.editorialRespository.save(editorial);
	}

	@Override
	public Editorial findById(Long id) {
		Editorial bean = this.editorialRespository.findById(id).get();
		return bean;
	}

	@Override
	public List<Editorial> finaLL() {
		return this.editorialRespository.findAll();
	}

	@Override
	public Editorial update(Editorial editorial, Long id) {
		Editorial bean = this.editorialRespository.findById(id).get();
		bean.setName(editorial.getName());
		return this.editorialRespository.save(bean);
	}


}
