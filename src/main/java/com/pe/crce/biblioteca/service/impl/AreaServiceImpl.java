package com.pe.crce.biblioteca.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.pe.crce.biblioteca.constant.BibliotecaConstant;
import com.pe.crce.biblioteca.constant.GetReportColumnsConstant;
import com.pe.crce.biblioteca.dto.AreaDTO;
import com.pe.crce.biblioteca.dto.HrefEntityDTO;
import com.pe.crce.biblioteca.dto.request.AreaDTORequest;
import com.pe.crce.biblioteca.errorhandler.EntityNotFoundException;
import com.pe.crce.biblioteca.export.ResourceExport;
import com.pe.crce.biblioteca.mapper.AreaMapper;
import com.pe.crce.biblioteca.model.Area;
import com.pe.crce.biblioteca.repository.AreaRepository;
import com.pe.crce.biblioteca.service.AreaService;
import com.pe.crce.biblioteca.util.BibliotecaResource;
import com.pe.crce.biblioteca.util.BibliotecaUtil;

@Transactional
@Service
public class AreaServiceImpl implements AreaService{

	final
	AreaRepository areaRepository;
	
	final
	BibliotecaUtil util;
	
	final
	AreaMapper areaMapper;
	
	final
	ResourceExport resourceExport;

	public AreaServiceImpl(AreaRepository areaRepository, BibliotecaUtil util, AreaMapper areaMapper,ResourceExport resourceExport) {
		this.areaRepository = areaRepository;
		this.util = util;
		this.areaMapper = areaMapper;
		this.resourceExport = resourceExport;
	}

	@Override
	public HrefEntityDTO save(AreaDTORequest dto) {
		Area area = this.areaRepository.save(this.areaMapper.toBean(dto));
		return this.util.createHrefFromResource(area.getId(), BibliotecaResource.AREA);
	}

	@Override
	public HrefEntityDTO update(AreaDTORequest dto, Long id) {
		Area area = this.areaRepository.findByIdAndState(id, BibliotecaConstant.STATE_ACTIVE)
				.orElseThrow(()-> new EntityNotFoundException("not found"));
		area.setDescription(dto.getDescription());
		return this.util.createHrefFromResource(this.areaRepository.save(area).getId(), BibliotecaResource.AREA);
	}

	@Override
	public HrefEntityDTO delete(Long id) {
		Area area = this.areaRepository.findByIdAndState(id, BibliotecaConstant.STATE_ACTIVE)
				.orElseThrow(()-> new EntityNotFoundException("not found"));
		area.setState(BibliotecaConstant.STATE_INACTIVE);
		return this.util.createHrefFromResource(this.areaRepository.save(area).getId(), BibliotecaResource.AREA);
	}

	@Transactional(readOnly = true)
	@Override
	public Page<AreaDTO> findByDescription(String description, Pageable pageable) {
		Page<Area> areas = this.areaRepository.findByDescriptionContainingIgnoreCaseAndState(BibliotecaUtil.preFormatCadena(description), BibliotecaConstant.STATE_ACTIVE,pageable);
		return areas.map((bean)-> this.areaMapper.toDto(bean));
	}

	@Transactional(readOnly = true)
	@Override
	public AreaDTO findById(Long id) {
		Area area = this.areaRepository.findByIdAndState(id, BibliotecaConstant.STATE_ACTIVE)
				.orElseThrow(()-> new EntityNotFoundException("not found"));
		return this.areaMapper.toDto(area);
	}

	@Override
	public List<AreaDTO> findByDescriptionFilter(String description) {
		return this.areaRepository.findByDescriptionContainingIgnoreCaseAndState(BibliotecaUtil.preFormatCadena(description), BibliotecaConstant.STATE_ACTIVE)
				.stream()
				.limit(15)
				.map((bean)-> areaMapper.toDto(bean))
				.collect(Collectors.toList());
	}

	@Override
	public File exportDataExcel(List<AreaDTO> areas) throws Exception {
		
		List<String> sheets = List.of(BibliotecaConstant.SHEET_AREA);
		
		Map<String, List<String>> colsBySheet = new HashMap<>();
		List<String> cols = List.of(GetReportColumnsConstant.COL_AREA_ID,GetReportColumnsConstant.COL_AREA_DESCRIPTION);

		
		colsBySheet.put(BibliotecaConstant.SHEET_AREA, cols);
		
		Map<String, List<Map<String, String>>> valuesBySheet = new HashMap<>();
		List<Map<String, String>> valoresHoja = new ArrayList<>();
		
		areas.forEach(row -> {
			Map<String, String> valuesHojaRow = new HashMap<>();
			valuesHojaRow.put(GetReportColumnsConstant.COL_AREA_ID, row.getId() == null ? BibliotecaConstant.VC_EMTY : row.getId().toString());
			valuesHojaRow.put(GetReportColumnsConstant.COL_AREA_DESCRIPTION, row.getDescription().toLowerCase());
			valoresHoja.add(valuesHojaRow);
		});
		valuesBySheet.put(BibliotecaConstant.SHEET_AREA, valoresHoja);
				
		return this.resourceExport.generateExcel(sheets, colsBySheet, valuesBySheet, BibliotecaConstant.REPORT_NAME_AREA_PAGINABLE);
	}
	
}
