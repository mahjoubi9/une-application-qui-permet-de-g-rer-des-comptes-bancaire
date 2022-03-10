package org.lsi.metier;

import java.util.List;

import org.lsi.dao.EmployeRepository;
import org.lsi.dao.GroupeRepository;
import org.lsi.entities.Employe;
import org.lsi.entities.Groupe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class EmployeMtierImpl implements EmployeMetier {
	
	@Autowired
	private EmployeRepository  employeRepository;
	private GroupeRepository groupRepository;
	
	
	@Override
	public List<Groupe> listGroup() {
		// TODO Auto-generated method stub
		return groupRepository.findAll();
	}
	@Override
	public Employe saveEmploye(Employe e) {
		// TODO Auto-generated method stub
		return employeRepository.save(e);
	}
	@Override
	public void updateEmploye(Employe e,Long id) {
		// TODO Auto-generated method stub
		employeRepository.save(e);
	}

	@Override
	public List<Employe> listEmployes() {
		// TODO Auto-generated method stub
		return employeRepository.findAll();
	}
	
	
	

	@Override
	public void deletemploye(Long codeEmploye) {
		// TODO Auto-generated method stub
		employeRepository.deleteById(codeEmploye);

	}

}
