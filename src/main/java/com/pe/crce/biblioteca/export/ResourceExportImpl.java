package com.pe.crce.biblioteca.export;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;
import com.pe.crce.biblioteca.constant.BibliotecaConstant;
import com.pe.crce.biblioteca.errorhandler.EntityUnauthorizedException;
import com.pe.crce.biblioteca.helper.ExcelExportHelper;

@Component
public class ResourceExportImpl implements ResourceExport {

	@Override
	public File generateExcel(List<String> sheets, Map<String, List<String>> colsBySheet,
			Map<String, List<Map<String, String>>> valuesBySheet, String fiLeName){
		try {
			Path temp = Files.createTempFile(fiLeName, BibliotecaConstant.FORMATO_EXCEL);
			return ExcelExportHelper.generateExcel(sheets, colsBySheet, valuesBySheet, temp.toString());
		} catch (Exception e) {
			throw new EntityUnauthorizedException(BibliotecaConstant.ERROR_REPORTE);
		}
	}

}
