package com.pe.crce.biblioteca;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.pe.crce.biblioteca.constant.BibliotecaConstant;
import com.pe.crce.biblioteca.model.Zone;
import com.pe.crce.biblioteca.repository.ZonaRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class AppBibliotecaApplicationTests {
	
	@Autowired
	private ZonaRepository zonaRepository;
	
	@Test
	void contLexoads() {
		Zone zone = this.zonaRepository.findByIdAndState(1l, BibliotecaConstant.STATE_ACTIVE).get();
		log.info("hola : "+ zone.toString());
	}

}
