package org.lsi.dao;

//import org.jboss.logging.Param;
import org.lsi.entities.Compte;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface CompteRepository extends JpaRepository<Compte, String> {
//	@Query(value="select code_client from client code_client where nom_client Like :x",nativeQuery = true)
//	public Long chercherClientId(@Param("x")String mc);
	
}
