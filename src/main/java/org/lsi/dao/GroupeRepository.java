package org.lsi.dao;

import org.lsi.entities.Groupe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface GroupeRepository extends JpaRepository<Groupe, Long> {
	@Query(value="select code_groupe from groupe code_groupe where nom_groupe Like :x",nativeQuery = true)
	public Long chercherGroupe(@Param("x")String mc);
}
