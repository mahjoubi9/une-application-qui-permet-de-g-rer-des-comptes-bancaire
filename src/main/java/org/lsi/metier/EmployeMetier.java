package org.lsi.metier;

import java.util.List;

import org.lsi.entities.Employe;
import org.lsi.entities.Groupe;
import org.springframework.ui.Model;

public interface EmployeMetier {
	
	public Employe saveEmploye(Employe e);
	public void updateEmploye(Employe e,Long id);
	
	public List<Employe> listEmployes();

	public void deletemploye(Long codeEmploye);
	public List<Groupe> listGroup();

	
}
