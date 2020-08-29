package org.sid.service.implement;

import java.util.Date;
import java.util.List;

import org.sid.dao.ClientRepository;
import org.sid.dao.CompteRepository;
import org.sid.entities.Client;
import org.sid.entities.Compte;
import org.sid.entities.CompteCourant;
import org.sid.entities.CompteEpargne;
import org.sid.service.CompteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CompteServiceImp implements CompteService {

	@Autowired
	private CompteRepository compteRepository;

	@Autowired
	private ClientRepository clientRepository;

	/*
	 * 
	 * 
	 * listComptes
	 * 
	 */
	@Override
	public List<Compte> listComptes() {
		return (List) compteRepository.findAll();
	}

	/*
	 * 
	 * with dropdownlis
	 */

	@Override
	public void createcomteCou(String codeCompte, Long soldeCompte, Long clientId, Long decouvert) {
		Compte cp = new CompteCourant();
		CompteCourant cCou = new CompteCourant();

		cCou.setCodeCompte(codeCompte);
		cCou.setSolde(soldeCompte);
		cCou.setDateCreation(new Date());
		cCou.setDecouvert(decouvert);

		// lade client Ã¼ber seine id
		Client client = clientRepository.findOne(clientId);
		cCou.setClient(client);
		compteRepository.save(cCou);

	}

	/*
	 * 
	 * with dropdownlis
	 */

	@Override
	public void createCompteEpa(String codeCompte, Long soldeCompte, Long clientId, Long taux) {
		CompteEpargne cEpa = new CompteEpargne();
		cEpa.setCodeCompte(codeCompte);
		cEpa.setSolde(soldeCompte);
		cEpa.setDateCreation(new Date());
		cEpa.setTaux(taux);
		// lade client Ã¼ber seine id
		Client client = clientRepository.findOne(clientId);
		cEpa.setClient(client);
		compteRepository.save(cEpa);

	}

}
