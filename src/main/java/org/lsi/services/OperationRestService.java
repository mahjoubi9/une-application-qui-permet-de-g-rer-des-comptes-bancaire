package org.lsi.services;

import java.util.List;

import org.lsi.dao.OperationRepository;
import org.lsi.entities.Client;
import org.lsi.entities.Compte;
import org.lsi.entities.Employe;
import org.lsi.entities.Operation;
import org.lsi.metier.CompteMetier;
import org.lsi.metier.OperationMetier;
import org.lsi.metier.PageOperation;
//import org.lsi.metier.PageOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class OperationRestService {
	
	@Autowired
	OperationMetier operationMetier;
	@Autowired
	OperationRepository operationRepository;

	@RequestMapping("/operations")
	public String index() {
		return "operations.html";
	}
	
	@RequestMapping("/consulterc")
	public String consulter1(Model model,String codeCompte, @RequestParam(value = "page", defaultValue = "0") int p,
			@RequestParam(value = "size", defaultValue = "5") int s) {
		Compte cp=operationMetier.getCompte(codeCompte);
		model.addAttribute("compte", cp);
		Page<Operation> pageoperation= operationRepository.findAll(PageRequest.of(p, s));	
		model.addAttribute("listemployes", pageoperation.getContent());
		int[] pages = new int[pageoperation.getTotalPages()];
		model.addAttribute("pages",pages);
		model.addAttribute("codeComptee",codeCompte);
		model.addAttribute("size",s);
		model.addAttribute("pageCourante",p);
		return "operations";
     // List<Operation> op = pageoperation.getOperations();
		                //List<Operation> op = operationMetier.listOperation();
		//List<Operation> op = operationMetier.listOperation(codeCompte);
		//List<Operation> op =operationMetier.listOperationcodeMetier(codeCompte);
      //model.addAttribute("listop", op);
         //cp.setOperations(op);
		
				//public Page<Operation> listOperation(String codeCompte, int page, int size);
	}
	  	
	@RequestMapping(value="/saveoperation",method=RequestMethod.POST) 
	public String saveoperation(Model model,
			@RequestParam String code,
			@RequestParam Long codeEmp,
			@RequestParam String cpte1,

			@RequestParam double montant,
			@RequestParam String typeOperation) {
		try {
		if(typeOperation.equals("virsement"))
		{
			operationMetier.verser(code, montant, codeEmp);
			
		}
		else if(typeOperation.equals("virement")) {
			operationMetier.virement(cpte1, code, montant, codeEmp);
		}
			
		if(typeOperation.equals("retrait")){
			
			operationMetier.retirer(code, montant, codeEmp);
		}

		}catch (Exception e) {
        model.addAttribute("error",e)	;	
}
		
		return "operations";
	} 
	 
}
	
	

