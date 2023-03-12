package com.pe.crce.biblioteca;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Principal {
	public static void main(String[] args) {
		Map<Integer, String> countries = new HashMap<>();
		countries.put(1, "Perú");
		countries.put(2, "Chile");
		countries.put(3, "Argentina");
		countries.put(4, "Paraguay");
		countries.put(5, "Uruguay");
		countries.put(6, "Brasil");
		countries.put(7, "Ecuador");
		countries.put(8, "Colombia");
		countries.put(9, "Venezuela");
		countries.put(10, "Bolivia");
		
		final Map<Integer, String> sortedMap = countries.entrySet()
				.stream()
				.sorted(Map.Entry.comparingByValue())
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
						(c,v)->c, LinkedHashMap::new));
		
		sortedMap.forEach((clave,valor)->{
			System.out.println(String.format("Nro: %02d País: %s", clave,valor));
		});

	}
}
