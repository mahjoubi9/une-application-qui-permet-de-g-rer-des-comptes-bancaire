package org.lsi.dao;


import org.lsi.entities.Client;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ClientRepository extends JpaRepository<Client, Long> {
	@Query(value="select code_client from client code_client where nom_client Like :x",nativeQuery = true)
	public Long chercherClientId(@Param("x")String mc);

}
