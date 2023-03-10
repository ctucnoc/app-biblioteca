package com.pe.crce.biblioteca.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.pe.crce.biblioteca.model.Library;

@Repository
public interface LibraryRepositorio extends JpaRepository<Library, Long> {

	@Query("select li from Library li where LOWER(li.name) like CONCAT('%',LOWER(?1),'%') and li.state = ?2")
	public List<Library> findByNameLikeJPA(String key_word, String state);
}
