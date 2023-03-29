package com.pe.crce.biblioteca;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.pe.crce.biblioteca.util.BibliotecaUtil;

@SpringBootTest
class AppBibliotecaApplicationTests {
	
	
	@Test
	void contLexoads() {
		String cadena = BibliotecaUtil.preFormatCadena("Hola Celia como estás té amó...");
		System.out.println(" cadena :: "+cadena.replace(" ", ""));
	}
}
