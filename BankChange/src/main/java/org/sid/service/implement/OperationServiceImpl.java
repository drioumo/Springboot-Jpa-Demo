package org.sid.service.implement;

import java.util.Date;
import java.util.List;

import org.sid.dao.ClientRepository;
import org.sid.dao.CompteRepository;
import org.sid.dao.OperationRepository;
import org.sid.entities.Client;
import org.sid.entities.Compte;
import org.sid.entities.CompteCourant;
import org.sid.entities.CompteEpargne;
import org.sid.entities.Operation;
import org.sid.entities.Retrait;
import org.sid.entities.Versement;
import org.sid.service.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OperationServiceImpl implements OperationService {
	@Autowired
	private CompteRepository compteRepository;

	@Autowired
	private OperationRepository operationRepository;
	@Autowired
	private ClientRepository clientRepository;

	@Override
	public Compte getCompte(String codeCompte) {
		Compte compte = compteRepository.findOne(codeCompte);
		if (compte == null)
			throw new RuntimeException("Compte introuvable"); // c'est une exception non surveiller
		return compte;
	}

	@Override
	public void versement(String codeCompte, double montant) {
		Compte compte = getCompte(codeCompte);
		Versement versement = new Versement(new Date(), montant, compte);

		operationRepository.save(versement);

		compte.setSolde(compte.getSolde() + montant);
		compteRepository.save(compte);
	}

	@Override
	public void retrait(String codeCompte, double montant) {

		Compte compte = getCompte(codeCompte);
		double facilitesCaisse = 0;

		if (compte instanceof CompteCourant) {

			facilitesCaisse = ((CompteCourant) compte).getDecouvert();

			if (compte.getSolde() + facilitesCaisse < montant)
				throw new RuntimeException("Slode insuffisant");

		}

		Retrait retrait = new Retrait(new Date(), montant, compte); // le retrait est une operation
		operationRepository.save(retrait);

		compte.setSolde(compte.getSolde() - montant);
		compteRepository.save(compte);

	}

	@Override
	public void virement(String codeCompteRetrait, String codeCompteVersement, double montant) {
		if (codeCompteRetrait == codeCompteVersement)
			throw new RuntimeException("Impossible : On ne peut pas effectuer un virement dans le meme compte");
		retrait(codeCompteRetrait, montant);
		versement(codeCompteVersement, montant);

	}

	@Override
	public Page<Operation> listOperationsCompte(String codeCompte, int page, int sizePage) { // page: est le numero de
																								// la page

		return operationRepository.listOperation(codeCompte, new PageRequest(page, sizePage));
	}

}