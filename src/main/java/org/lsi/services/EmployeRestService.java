package org.lsi.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

//import org.fstt.lsi.thymeleaf.entities.Produit;
import org.lsi.dao.ClientRepository;
import org.lsi.dao.EmployeRepository;
import org.lsi.dao.GroupeRepository;
import org.lsi.dao.OperationRepository;
import org.lsi.dao.GroupeRepository;
import org.lsi.entities.Client;
import org.lsi.entities.Employe;
import org.lsi.entities.Groupe;
import org.lsi.metier.EmployeMetier;
import org.lsi.metier.GroupeMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

    @Controller
    
         //@RestController
      public class EmployeRestService {
	@Autowired
	private EmployeMetier employeMetier;
	private EmployeRepository  employeRepository;
	private GroupeRepository groupeRepository;
	private GroupeMetier groupeMetier;

	@Autowired
	private GroupeRepository groupRepository;

	@Autowired
	private GroupeRepository groupRepositoryy;
	@Autowired
	private EmployeRepository  employeRepositoryyyyy;

	
	@RequestMapping(value="/newemploye",method=RequestMethod.GET) 
	public String index(Model model) {
		//List<Groupe> Groupe = groupeMetier.listGroup();
		List<Groupe> Groupes = groupRepository.findAll();
		model.addAttribute("Groupes", Groupes);
		return "newemlpoye.html";
	}
	@RequestMapping(value="/affectation") 
	public String affectation(Model model,Long codeEmploye) {
		
		List<Employe> employes = employeRepositoryyyyy.findAll();
		 Employe employeee  =new Employe();
		for( Employe employe : employes ) {
			  if(employe.getCodeEmploye()==codeEmploye)
				  employeee=employe;
			}
    model.addAttribute("employe",employeee);
    
		return "affectation";
	}
	@RequestMapping(value="/saveafectation",method=RequestMethod.GET) 
	public String saveafectation(Model model,String nomGroupe1,String nomGroupe2,Long CodeEmploye) {
		//List<Groupe> Groupe = groupeMetier.listGroup();
		List<Groupe> Groupes = new ArrayList();
		 Employe employe= employeRepositoryyyyy.findById(CodeEmploye).get();
		 //List<Groupe> Groupes = employe.getGroupes();
	     for( Groupe groupe : employe.getGroupes() ) {
    	 System.out.println(groupe.getCodeGroupe());
		  Groupes.add(groupe);
		}
		 Long Groupe22 = null ;
		 Long Groupe11 = null ;
//		Groupe Groupe1 = groupRepository.chercherGroupe(nomGroupe1);
//		Groupe Groupe2 = groupRepository.chercherGroupe(nomGroupe2);
		 if(nomGroupe2!=null) {
		Groupe22 = groupRepository.chercherGroupe(nomGroupe2);}
		 if(nomGroupe1!=null) {
		 Groupe11 = groupRepository.chercherGroupe(nomGroupe1);}
//		System.out.println(nomGroupe1);
//		System.out.println(nomGroupe2);
//      	System.out.println(Groupe11);
//		System.out.println(Groupe22);
//		System.out.println(groupRepositoryy.findById(Groupe11).get().getNomGroupe());
//		System.out.println(groupRepositoryy.findById(Groupe22).get().getNomGroupe());
		if(Groupe11!=null)
			Groupes.add(groupRepositoryy.findById(Groupe11).get()) ;
		if(Groupe22!=null)
			Groupes.add(groupRepositoryy.findById(Groupe22).get()) ;
		
//        Groupes.add(Groupe2);
//        Groupes.add(Groupe1);
	     //List listgroupes = new ArrayList();
	     //List<Groupe> listgroupes = null ;
	     //listgroupes.add(groupes);
	     	
	     employe.setGroupes(Groupes);
//	     employeRepositoryyyyy.save(employe);
	     employeMetier.updateEmploye(employe,employe.getCodeEmploye());
		return "redirect:/employes";
		// booksRepository.findById(id).get();
				
	}
	

	@GetMapping(path="/deleteEmploye")
	private String deleteE(Long codeEmploye) {
	     System.out.println(" deleted");

	     employeMetier.deletemploye(codeEmploye);
		
	return "redirect:/employes";
	 }
	
	
	
	@GetMapping(path="/employe")
	private String save(
			@RequestParam(name="nomEmploye",defaultValue="")String nomEmploye, 
			 @RequestParam(name="employeSup",defaultValue="3")double employeSup,
	 @RequestParam(name="codeGroupe",defaultValue="1")Long codeGroupe){
		//ajouté employes super
		List<Employe> employes = employeRepositoryyyyy.findAll();
		 Employe employeSupp  =new Employe();
		for( Employe employe : employes ) {
			  if(employe.getCodeEmploye()==employeSup)
				  employeSupp=employe;
			}
     System.out.println("in save");
     Employe employe  =new Employe(nomEmploye);
     employe.setEmployeSup(employeSupp);
//     //ajouté employe a un group
//     List<Groupe> Groupes = groupRepository.findAll();
//     Groupe  groupes = new Groupe();
//     
//     for( Groupe groupe : Groupes ) {
//    	 System.out.println(groupe.getCodeGroupe());
//		  if(groupe.getCodeGroupe()==codeGroupe)
//			  System.out.println(groupe.getNomGroupe());
//			  groupes=groupe;
//		}
//     System.out.println(groupes.getCodeGroupe());
//     List listgroupes = new ArrayList();
//     //List<Groupe> listgroupes = null ;
//     listgroupes.add(groupes);
//     employe.setGroupes(listgroupes);
     employeMetier.saveEmploye(employe);
     
     
    
     return "redirect:/employes";  
     
	}
	/*
	
	@GetMapping(path="/employe")
	private String save( @RequestParam(name="codeEmploye",defaultValue="")double codeEmploye, 
			             @RequestParam(name="nomEmploye",defaultValue="")String nomEmploye) {
     System.out.println("in save");
    // employeRepository.save(new Employe(codeEmploye,nomEmploye));
     employeRepository.save(new Employe());
     return "redirect:/employes";
	 }
	
	
	*/
	@RequestMapping(value="/employes",method=RequestMethod.GET) 
	public String listEmployes(Model model ,@RequestParam(value = "page", defaultValue = "0") int p,
			@RequestParam(value = "size", defaultValue = "5") int s,
			@RequestParam(value = "motCle", defaultValue = "") String motCle) {
//		System.out.println(motCle);
//		System.out.println("dddddddddddddddddddddddddddddddddd");
		Page<Employe> pageEmploye= employeRepositoryyyyy.findAll(PageRequest.of(p, s));	
//		System.out.println(motCle);
		Long employechercheid = employeRepositoryyyyy.chercherEmployeId(motCle);
	    // Page<Employe> employes= employeRepository.findAll(new PageRequest(p,s));
//		System.out.println(employechercheid);
		if(employechercheid !=null) {
			Employe employe = employeRepositoryyyyy.findById(employechercheid).get();
			model.addAttribute("employecherche",employe);
			return "employecherche";
		}else {
			model.addAttribute("listemploye", pageEmploye.getContent());
			int[] pages = new int[pageEmploye.getTotalPages()];
			model.addAttribute("pages",pages);
			model.addAttribute("size",s);
			model.addAttribute("pageCourante",p);
			return "employes";}
		
		/* model.addAttribute("currentPage", page); 
	      model.addAttribute("size", size); 
	      model.addAttribute("motCle", motCle); 
	      model.addAttribute("pages", new int[employes.getTotalPages()]); */
//		List<Employe> employes = employeMetier.listEmployes();
//		model.addAttribute("listEmployes", employes);
//		return "employes";
	}
	
	
	
	
}
