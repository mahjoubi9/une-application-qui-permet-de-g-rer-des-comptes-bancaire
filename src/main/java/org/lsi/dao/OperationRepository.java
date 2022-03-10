package org.lsi.dao; 
import java.util.List;

import org.lsi.entities.Compte;
import org.lsi.entities.Operation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param; 
public interface OperationRepository extends JpaRepository<Operation, Long> {


//@Query("select * from Operation o where o.compte.codeCompte Like:x order by o.dateOperation desc")
//public List<Operation> listOperationcode(@Param("x")String codeCompte);
	
}
