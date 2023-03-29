package com.pe.crce.biblioteca.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pe.crce.biblioteca.model.SpecimenState;

@Repository
public interface SpecimenStateRepository extends JpaRepository<SpecimenState, Long>{

	public Page<SpecimenState> findByDescriptionContainingAndState(String description, String state, Pageable pageable);
	
	public Optional<SpecimenState> findByIdAndState(Long id, String state);
}
