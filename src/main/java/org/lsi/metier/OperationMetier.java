package org.lsi.metier;


import java.util.List;

import org.lsi.entities.Compte;
import org.lsi.entities.Operation;
import org.springframework.data.domain.Page;

public interface OperationMetier {
	
	public boolean verser(String code,double montant, Long codeEmp);
	public boolean retirer(String code,double montant, Long codeEmp);
	public boolean virement(String cpte1,String code,double solde, Long codeEmp);
	public List<Operation> listOperation();
	//public List<Operation> listOperationcodeMetier(String codeCompte);
//public Page<Operation> listOperation(String codeCompte, int page, int size);
//PageOperation getOperation(String codeCompte, int page, int size);
//PageOperation getOperation(String codeCompte, int page, int size);
	public Compte getCompte(String code);


}
