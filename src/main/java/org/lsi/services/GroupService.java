/*package org.lsi.services;

import java.util.List;

import org.lsi.dao.EmployeRepository;
import org.lsi.dao.GroupeRepository;
import org.lsi.entities.Employe;
import org.lsi.entities.Groupe;
import org.lsi.metier.EmployeMetier;
import org.lsi.metier.GroupeMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GroupService {
    @Autowired

    private GroupeMetier groupeMetier;
	private GroupeRepository  groupeRepository;
	
	@GetMapping("/groupP")
	public String index22() {
		return "group";
	}	
	
	
	
	@GetMapping("/newgroup")
	public String index2() {
		return "newgroup";
	}	
	
	
	@PostMapping(path="/savegroupe")
	private String save( @RequestParam(name="nomGroupe",defaultValue="")String nomGroupe 
			             ) {
     //System.out.println("in save");
     groupeMetier.saveGroup(new Groupe(nomGroupe));
     return "redirect:/group";  
     
	}
	
	
	
	@GetMapping(path="/deletegroup") 
	private String delete(Long codeGroupe) { 
	 
		groupeMetier.deletegroup(codeGroupe); 
	return "redirect:/group"; 
	 } 
	
	
	
	@RequestMapping(value="/group",method=RequestMethod.GET) 
	public String listgroup(Model model) {
		List<Groupe> Groupe = groupeMetier.listGroup();
		model.addAttribute("listgroup", Groupe);
		return "group";
	}
	
}	
	
	
	
	

*/
  package org.lsi.services;
 

import java.util.List;

import javax.validation.Valid;

import org.lsi.dao.EmployeRepository;
import org.lsi.dao.GroupeRepository;
import org.lsi.entities.Employe;
import org.lsi.entities.Groupe;
import org.lsi.metier.EmployeMetier;
import org.lsi.metier.GroupeMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GroupService {
    @Autowired

    private GroupeMetier groupeMetier;
	private GroupeRepository  groupeRepository;
	@Autowired
	private GroupeRepository groupRepositoryy;
	@GetMapping("/groupP")
	public String index22() {
		return "group";
	}	
	
	
	
	@RequestMapping(value = "/newgroup",method = RequestMethod.GET)
	public String index2(Model model) {
		model.addAttribute("group",new Groupe());
		
		return "newgroup";
	}	
	
	

	@RequestMapping(value = "/savegroupe",method = RequestMethod.POST)
	private String save( Groupe group) {
     //System.out.println("in save");
     groupeMetier.saveGroup(group);
     return "redirect:/group";  
     
	}

	
	
	@RequestMapping(value="/group",method=RequestMethod.GET) 
	public String listgroup(Model model,@RequestParam(value = "page", defaultValue = "0") int p,
			@RequestParam(value = "size", defaultValue = "5") int s,
			@RequestParam(value = "motCle", defaultValue = "") String motCle) {
		Page<Groupe> pageGroupe= groupRepositoryy.findAll(PageRequest.of(p, s));
		//List<Groupe> Groupe = groupeMetier.listGroup();
		Long groupechercheid = groupRepositoryy.chercherGroupe(motCle);
		//model.addAttribute("listgroup", Groupe);
		if(groupechercheid !=null) {
			Groupe groupe = groupRepositoryy.findById(groupechercheid).get();
			model.addAttribute("groupecherche",groupe);
			return "groupecherche";
		}else {
			model.addAttribute("listgroupe", pageGroupe.getContent());
			int[] pages = new int[pageGroupe.getTotalPages()];
			model.addAttribute("pages",pages);
			model.addAttribute("size",s);
			model.addAttribute("pageCourante",p);
			return "group";}
	}
	

	@GetMapping(path="/deletegroup") 
	private String delete(Long codeGroupe) { 
	 
		groupeMetier.deletegroup(codeGroupe); 
	return "redirect:/group"; 
	 } 
	
}
