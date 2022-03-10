package org.lsi.dao;

import org.lsi.entities.Employe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EmployeRepository extends JpaRepository<Employe, Long> {

	@Query(value="select code_employe from employe code_employe where nom_employe Like :x",nativeQuery = true)
	public Long chercherEmployeId(@Param("x")String mc);
}
