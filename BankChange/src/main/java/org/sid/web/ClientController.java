package org.sid.web;

import java.util.List;

import org.sid.entities.Client;
import org.sid.service.ClientService;
import org.sid.service.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/clients")
public class ClientController {
	/*
	 * @Autowired private ClientMetier clientMetier;
	 */

	@Autowired
	private ClientService clientMetier;

	@RequestMapping("/listClient")

	public String listPages(Model model,

			@RequestParam(name = "page", defaultValue = "0") int page,

			@RequestParam(name = "size", defaultValue = "5") int size)

	{ // cette methode retourne une vue tous simplement

		try {

			List<Client> cl = clientMetier.listClient();
			Page<Client> pageClients = clientMetier.listClientPage(page, size);
			model.addAttribute("listClient", pageClients.getContent());
			int[] pages = new int[pageClients.getTotalPages()];
			model.addAttribute("pages", pages);
			model.addAttribute("client", cl);

		} catch (Exception e) {
			model.addAttribute("exception", e);
		}

		return "listClient";
	}

}
