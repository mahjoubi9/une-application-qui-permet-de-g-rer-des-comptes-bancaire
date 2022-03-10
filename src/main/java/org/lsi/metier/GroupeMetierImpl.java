package org.lsi.metier;


import org.lsi.dao.GroupeRepository;
import org.lsi.entities.Groupe;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
@Service

public class GroupeMetierImpl implements GroupeMetier {

	@Autowired
	private GroupeRepository groupRepository;

	@Override
	public Groupe saveGroup(Groupe g) {
		// TODO Auto-generated method stub
		return groupRepository.save(g);
	}
	
	
	
	
	
	@Override
	public List<Groupe> listGroup() {
		// TODO Auto-generated method stub
		return groupRepository.findAll();
	}

	@Override
	public void deletegroup(long id) {
		// TODO Auto-generated method stub
		groupRepository.deleteById(id);
	}
}




