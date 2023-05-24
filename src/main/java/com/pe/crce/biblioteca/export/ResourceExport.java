package com.pe.crce.biblioteca.export;

import java.io.File;
import java.util.List;
import java.util.Map;

public interface ResourceExport {

	public File generateExcel(List<String> sheets, Map<String, List<String>> colsBySheet,
			Map<String, List<Map<String, String>>> valuesBySheet, String fileName);
}
