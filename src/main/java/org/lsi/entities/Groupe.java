package org.lsi.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.springframework.data.relational.core.mapping.Column;

@Entity

public class Groupe implements Serializable {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	//@Column("isFeatured")
	@Column(keyColumn="code_groupe")
	private Long codeGroupe;
	@Column(keyColumn="nom_groupe")
	private String nomGroupe;
	
	@ManyToMany(mappedBy="groupes")
	
	private Collection<Employe> employe;
	public Groupe(String nomGroupe) {
		super();
		this.nomGroupe = nomGroupe;
	}
	public Groupe() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Long getCodeGroupe() {
		return codeGroupe;
	}
	public void setCodeGroupe(Long codeGroupe) {
		this.codeGroupe = codeGroupe;
	}
	public String getNomGroupe() {
		return nomGroupe;
	}
	public void setNomGroupe(String nomGroupe) {
		this.nomGroupe = nomGroupe;
	}
	public Collection<Employe> getEmploye() {
		return employe;
	}
	public void setEmploye(Collection<Employe> employe) {
		this.employe = employe;
	}

}
