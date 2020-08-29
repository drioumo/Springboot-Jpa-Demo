package org.sid.entities;

import org.springframework.ui.Model;

public class CompteModel {

String typeCompte;
String codeCompte;
Long soldeCompte;
Long codeClient;
Long taux;
Long decouvert;

public String getTypeCompte() {
	return typeCompte;
}
public void setTypeCompte(String typeCompte) {
	this.typeCompte = typeCompte;
}
public String getCodeCompte() {
	return codeCompte;
}
public void setCodeCompte(String codeCompte) {
	this.codeCompte = codeCompte;
}
public Long getSoldeCompte() {
	return soldeCompte;
}
public void setSoldeCompte(Long soldeCompte) {
	this.soldeCompte = soldeCompte;
}
public Long getCode() {
	return codeClient;
}
public void setCode(Long code) {
	this.codeClient = code;
}
public Long getTaux() {
	return taux;
}
public void setTaux(Long taux) {
	this.taux = taux;
}
public Long getDecouvert() {
	return decouvert;
}
public void setDecouvert(Long decouvert) {
	this.decouvert = decouvert;
}



}
