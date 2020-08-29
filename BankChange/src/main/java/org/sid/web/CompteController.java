package org.sid.web;

import java.util.List;

import org.sid.entities.Client;
import org.sid.entities.Compte;
import org.sid.entities.CompteEpargne;
import org.sid.entities.CompteModel;
import org.sid.service.ClientService;
import org.sid.service.CompteService;
import org.sid.service.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/comptes")
public class CompteController {
	@Autowired
	private CompteService compteMetier;
	@Autowired
	private ClientService clientMetier;

	@RequestMapping(value = "/listComptes")
	public String listCompte(Model model) {

		List<Compte> listC = compteMetier.listComptes();

		model.addAttribute("listCompte", listC);

		return "listComptes";
	}

	@RequestMapping(value = "/initCreateCompte", method = RequestMethod.GET)
	public String initCreateCompte() {
		return "createCompte";
	}

	/*
	 * 
	 * create Compte with dropdowlist
	 * 
	 * 
	 */
	@RequestMapping(value = "/initCreateCompte2", method = RequestMethod.GET)
	public String initCreateCompte2(Model model) {

		List<Client> cl = clientMetier.listClient();
		model.addAttribute("listClient", cl);
//		model.addAttribute("code", 0);

		CompteModel comM = new CompteModel();
		model.addAttribute("compteModel", comM);
		return "createCompte2";
	}

	// create compte method

	@RequestMapping(value = "/saveCompte2", method = RequestMethod.POST)
	public String saveCompte2(Model model, CompteModel comptmod) {
		System.out.println(comptmod.getTaux());
		System.out.println(comptmod.getDecouvert());
		System.out.println(comptmod.getCode());

		if (comptmod.getTypeCompte().equals("Epagne")) {
			compteMetier.createCompteEpa(comptmod.getCodeCompte(), comptmod.getSoldeCompte(), comptmod.getCode(),
					comptmod.getTaux());

		} else if (comptmod.getTypeCompte().equals("Courant")) {
			compteMetier.createcomteCou(comptmod.getCodeCompte(), comptmod.getSoldeCompte(), comptmod.getCode(),
					comptmod.getDecouvert());

		}

		List<Compte> listC = compteMetier.listComptes();
		model.addAttribute("listCompte", listC);

		return "listComptes";
	}
}
