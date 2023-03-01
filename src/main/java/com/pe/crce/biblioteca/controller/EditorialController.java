package com.pe.crce.biblioteca.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pe.crce.biblioteca.model.Editorial;
import com.pe.crce.biblioteca.service.EditorialService;

@RestController
@RequestMapping("/editorial")
@CrossOrigin("*")
public class EditorialController {

	private EditorialService editorialService;

	public EditorialController(EditorialService editorialService) {
		this.editorialService = editorialService;
	}
	
	@GetMapping("/findAll")
	public List<Editorial> findAll(){
		return this.editorialService.finaLL();
	}
	
	@GetMapping("/findById/{id}")
	public Editorial findById(@PathVariable Long id) {
		return this.editorialService.findById(id);
	}
	
	
}
