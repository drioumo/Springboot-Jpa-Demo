package org.sid;

import java.util.Date;

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
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

@SpringBootApplication

public class VotreBanqueApplication implements CommandLineRunner { 
																	
																	
	@Autowired
	private ClientRepository iClientRepository;

	@Autowired
	private CompteRepository iCompteRepository;

	@Autowired
	private OperationRepository iOperationRepository;

	// pour tester la couche metier
	@Autowired
	private OperationService iBanqueMetier;

	public static void main(String[] args) {

		SpringApplication.run(VotreBanqueApplication.class, args);

		
	}

	@Override 
	public void run(String... arg0) throws Exception {

		Client client1 = iClientRepository.save(new Client("Hassan", "hassan@gmail.com")); // ici, la methode save de
																							
		Client client2 = iClientRepository.save(new Client("nassima", "nassima@gmail.com"));

		Compte c1 = iCompteRepository.save(new CompteCourant("c1", new Date(), 90000.0, client1, 6000.0));
		Compte c2 = iCompteRepository.save(new CompteEpargne("c2", new Date(), 6000.0, client2, 5.5));
		Client client3 = iClientRepository.save(new Client("Hassan", "hassan@gmail.com")); // ici, la methode save de
																							
		Client client4 = iClientRepository.save(new Client("nassima", "nassima@gmail.com"));

		Compte c3 = iCompteRepository.save(new CompteCourant("c3", new Date(), 90000.0, client1, 6000.0));
		Compte c4 = iCompteRepository.save(new CompteEpargne("c4", new Date(), 6000.0, client2, 5.5));
		Client client5 = iClientRepository.save(new Client("Hassan", "hassan@gmail.com")); // ici, la methode save de
																							
		Client client6 = iClientRepository.save(new Client("nassima", "nassima@gmail.com"));

		Compte c5 = iCompteRepository.save(new CompteCourant("c5", new Date(), 90000.0, client1, 6000.0));
		Compte c6 = iCompteRepository.save(new CompteEpargne("c6", new Date(), 6000.0, client2, 5.5));
		Client client7 = iClientRepository.save(new Client("Hassan", "hassan@gmail.com")); // ici, la methode save de
																							
		Client client8 = iClientRepository.save(new Client("nassima", "nassima@gmail.com"));

		Compte c7 = iCompteRepository.save(new CompteCourant("c7", new Date(), 90000.0, client1, 6000.0));
		Compte c8 = iCompteRepository.save(new CompteEpargne("c8", new Date(), 6000.0, client2, 5.5));

		// operations de compte1
		iOperationRepository.save(new Versement(new Date(), 9000.0, c1));
		iOperationRepository.save(new Versement(new Date(), 6000.0, c2));
		iOperationRepository.save(new Versement(new Date(), 2300.0, c2));

		iOperationRepository.save(new Retrait(new Date(), 9000.0, c1));

		// operations de compte2
		iOperationRepository.save(new Versement(new Date(), 2300.0, c2));
		iOperationRepository.save(new Versement(new Date(), 400.0, c2));
		iOperationRepository.save(new Versement(new Date(), 2300.0, c2));

		iOperationRepository.save(new Retrait(new Date(), 3000.0, c2));

	
		iOperationRepository.listOperation("c1", new PageRequest(2, 2));
		

	}
}
