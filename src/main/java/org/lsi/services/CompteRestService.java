package org.lsi.services;

import java.util.Date;
//import java.rmi.server.Operation;
import java.util.List;

import javax.websocket.server.PathParam;

import org.lsi.dao.ClientRepository;
import org.lsi.dao.CompteRepository;
import org.lsi.dao.EmployeRepository;
import org.lsi.dao.OperationRepository;
import org.lsi.entities.Client;
import org.lsi.entities.Compte;
import org.lsi.entities.CompteCourant;
import org.lsi.entities.CompteEpargne;
import org.lsi.entities.Employe;
import org.lsi.entities.Groupe;
import org.lsi.entities.Operation;
import org.lsi.metier.CompteMetier;
import org.lsi.metier.OperationMetier;
import org.lsi.metier.PageOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class CompteRestService {
   

	@Autowired
	private CompteMetier compteMetier;
	private CompteRepository  compteRepository;
	private PageOperation pageoperation;
	private  OperationRepository operationRepository ;
	@Autowired
	private  EmployeRepository employeRepository ;
	private OperationMetier operationMetier ;
	@Autowired 
	private ClientRepository clientRepository;

	@RequestMapping(value="/createcompte",method=RequestMethod.GET) 
	public String saveoperation(Model model,
			@RequestParam String codeCompte,
			@RequestParam Long codeClient,

			@RequestParam double solde,
			@RequestParam(value = "taux", defaultValue = "0.0") double taux,
			@RequestParam(value = "decouvert", defaultValue = "0.0") double decouvert,
			@RequestParam String type,String nomEmploye) {
		Compte cp=compteMetier.getCompte(codeCompte);
		try {
		if(type.equals("CC"))	
		{
			Long employeid = employeRepository.chercherEmployeId(nomEmploye);
			List<Employe> employes = employeRepository.findAll();
			 Employe employeee  =new Employe();
			for( Employe employe : employes ) {
				  if(employe.getCodeEmploye()==employeid)
					  employeee=employe;
				}
			Client client = clientRepository.findById(codeClient).get();
		     System.out.println(" saved");
		     CompteCourant compteCourant =new CompteCourant(codeCompte,new Date() , solde, decouvert);
		     compteCourant.setEmploye(employeee);
		     compteCourant.setClient(client);
			compteMetier.saveCompte(compteCourant);
			
		}
		else if(type.equals("CE")) {
		     System.out.println(" saved");
		     Long employeid = employeRepository.chercherEmployeId(nomEmploye);
				List<Employe> employes = employeRepository.findAll();
				 Employe employeee  =new Employe();
				for( Employe employe : employes ) {
					  if(employe.getCodeEmploye()==employeid)
						  employeee=employe;
					}
				Client client = clientRepository.findById(codeClient).get();
			     System.out.println(" saved");
			     CompteEpargne CompteEpargne =new CompteEpargne(codeCompte,new Date() , solde, taux);
			     CompteEpargne.setEmploye(employeee);
			     CompteEpargne.setClient(client);
				compteMetier.saveCompte(CompteEpargne);
		}
			


		}catch (Exception e) {
        model.addAttribute("error",e)	;	
}
		
	     return "redirect:/compte"; 
		//return compteMetier.getCompte(codeCompte);

	} 
	
	
	@RequestMapping("/consulter")
	public String consulter(Model model,String codeCompte) {
		try {
		Compte cp=compteMetier.getCompte(codeCompte);
		model.addAttribute("compte",cp);}
		catch (Exception e) {
			//System.out.println("ce compte n'existe pas");

			model.addAttribute("exception",e);

			} return "compte";
	}
	
	//opaeration:

	
	
	
	@RequestMapping("/newcompte")
	public String index1() {
		return "newcompte.html";
	}
	//-------------------------
//	@RequestMapping(value = "/newcompte",method = RequestMethod.GET)
//	public String index1(Model model) {
//		model.addAttribute("compte",new Compte());
//		
//		return "newcompte";
//	}	
	
//	
//
//	@RequestMapping(value = "/savegroupe",method = RequestMethod.POST)
//	private String save( Groupe group) {
//     //System.out.println("in save");
//     groupeMetier.saveGroup(group);
//     return "redirect:/group";  
//     
//	}
	//------------------

	
		
	@RequestMapping("/compte")
	public String index() {
		return "compte";
	}
	
	
}
