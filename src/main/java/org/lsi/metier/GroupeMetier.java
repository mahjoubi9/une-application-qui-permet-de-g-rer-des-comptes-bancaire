package org.lsi.metier;

import java.util.List;

import org.lsi.entities.Groupe;


public interface GroupeMetier {
	public Groupe saveGroup(Groupe g);
	public void deletegroup(long codeGroupe);

	public List<Groupe> listGroup();
}
