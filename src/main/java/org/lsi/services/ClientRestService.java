package org.lsi.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//import org.fstt.lsi.thymeleaf.dao.ProduitRepository;
//import org.fstt.lsi.thymeleaf.entities.Produit;
import org.lsi.dao.ClientRepository;
import org.lsi.dao.CompteRepository;
import org.lsi.dao.EmployeRepository;
import org.lsi.entities.Client;
import org.lsi.entities.Compte;
import org.lsi.entities.Employe;
import org.lsi.entities.Groupe;
import org.lsi.metier.ClientMetier;
import org.lsi.metier.CompteMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.query.Param;
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

//import net.javaguides.springboot.model.Employee;

    @Controller
public class ClientRestService {
	
	@Autowired
	private ClientRepository ClientRepository;
	private ClientMetier clientmetier ;
	@Autowired
	private EmployeRepository  employeRepositoryyyyy; 
	@Autowired
	private CompteMetier compteMetier;
	@Autowired
	private CompteRepository compteRepository;
	@Autowired
	private CompteRepository compteRepositoryyy;
	
	@GetMapping("/")
	public String index() {
		return "cl";
	}
	
	/*@GetMapping("/newcl")
	public String index22() {
		return "creerclient";
	}
 */
	
	 

	@GetMapping(path="/deleteclient")
	private String deleteE(Long codeClient) {
	     System.out.println(" deleted");

	     ClientRepository.deleteById(codeClient); 
		
	return "redirect:/clients";
	 }

//	@RequestMapping(value = "/saveclient",method = RequestMethod.GET)
//	private String save( Client Client
//			             ) {
//     //System.out.println("in save");
//		ClientRepository.save(Client);
//	     return "redirect:/clients";  
//     
//	}
	
	@RequestMapping(value = "/saveclient",method = RequestMethod.GET)
	private String save( Client Client,String codeCompte
			             ) {
		Compte compte=compteMetier.getCompte(codeCompte);
		compte.setClient(Client);
		//System.out.println(compte.getClient().getNomClient());
		// TODO Auto-generated method stub
		
		List<Compte> comptes = new ArrayList();
		comptes.add(compte);
		Client.setComptes(comptes);
		//compteRepositoryyy.save(compte);
		ClientRepository.save(Client);
	     return "redirect:/clients";  
     
	}
	@RequestMapping(value = "/affectationdeclient",method = RequestMethod.GET)
	private String affectationdeclient( Model model,Long codeClient 
			             ) {
     //System.out.println("in save");
		Client client = ClientRepository.findById(codeClient).get();
	//	System.out.println(client.getNomClient());
		model.addAttribute("client",client);
	     return "addcomptettoclient";  
     
	}	
	@RequestMapping(value = "/addcompte",method = RequestMethod.GET)
	private String addcompte( Model model,@RequestParam(value = "codeCompte")String codeCompte ,@RequestParam(value = "codeClient", defaultValue = "5")Long codeClient
            ) {
		System.out.println(codeClient);
if(codeCompte!=null) {
		Compte compte=compteMetier.getCompte(codeCompte);
		List<Compte> comptes = new ArrayList();
		comptes.add(compte);
		System.out.println(codeCompte);
		Client client = ClientRepository.findById(codeClient).get();
		System.out.println(client.getNomClient());
		System.out.println(client.getNomClient());
		client.setComptes(comptes);
		System.out.println(client.getNomClient());
		ClientRepository.save(client);
		
		
}
//		
//		
//Client client = ClientRepository.findById(codeClient).get();
////	System.out.println(client.getNomClient());
//model.addAttribute("client",client);
return "redirect:/clients";

}
	
//	@RequestMapping(value = "/affectationdeclient",method = RequestMethod.GET)
//	private String affectationdeclient( Long codeClient 
//			             ) {
//		Client client = ClientRepository.findById(codeClient).get();
//		
//		
//		ClientRepository.save(Client);
//	     return "redirect:/clients";  
//     
//	}
	
	@RequestMapping(value = "/newcl",method = RequestMethod.GET)
	public String index22(Model model) {
		model.addAttribute("client",new Client());
		
		return "creerclient";
	}	
	
	/*@PostMapping(value = "/saveclient")
	private String save( Client client) {
     //System.out.println("in save");
		clientmetier.saveClient(client);
     return "redirect:/index";  
     
	}*/

	  
	@GetMapping("/clients")		
	public String index1(Model model, @RequestParam(value = "page", defaultValue = "0") int p,
			@RequestParam(value = "size", defaultValue = "5") int s,
			@RequestParam(value = "motCle", defaultValue = "") String motCle) {
		Page<Client> pageclient= ClientRepository.findAll(PageRequest.of(p, s));	
		//Page<Client> PageClients = ClientRepository.chercher("%"+motCle+"%",PageRequest.of(p, s));
		Long clientchercheid = ClientRepository.chercherClientId(motCle);
		System.out.println(clientchercheid);
		if(clientchercheid !=null) {
			Client client = ClientRepository.findById(clientchercheid).get();
			model.addAttribute("clientcherche",client);
			return "clientcherche" ;
		}else {
		model.addAttribute("listclient", pageclient.getContent());
		int[] pages = new int[pageclient.getTotalPages()];
		model.addAttribute("pages",pages);
		model.addAttribute("size",s);
		model.addAttribute("pageCourante",p);
		return "index.html";}
		
	}	
//	@RequestMapping(value = "/clients")
//	public String produits(Model model, @RequestParam(value = "page", defaultValue = "0") int p,
//			@RequestParam(value = "size", defaultValue = "5") int s,
//			@RequestParam(value = "motCle", defaultValue = "redwane") String mc) {
//
//		Page<Client> PageClients = ClientRepository.chercher("%"+mc+"%",PageRequest.of(p, s));
//
//		model.addAttribute("listclients", PageClients.getContent());
//
//		int[] pages = new int[PageClients.getTotalPages()];
//		model.addAttribute("pages",pages);
//		model.addAttribute("size",s);
//		model.addAttribute("pageCourante",p);
//		model.addAttribute("motCle",mc);
//		return "index";
//	}
	@GetMapping(path="/listerCompteClient")
	public String listerCompteClient( Long code, Model model) {
		Client client=ClientRepository.findById(code).orElse(null);
		model.addAttribute("clients",client);
			return "listescomptes";
		}

    
	
}


