package org.sid.service;

import java.util.List;

import org.sid.entities.Compte;

public interface CompteService {

	public List<Compte> listComptes();

	public void createcomteCou(String codeCompte, Long soldeCompte, Long clientId, Long decouvert);

	public void createCompteEpa(String codeCompte, Long soldeCompte, Long clientId, Long taux);
}
