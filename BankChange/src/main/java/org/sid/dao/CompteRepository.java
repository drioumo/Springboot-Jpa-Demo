package org.sid.dao;

import org.sid.entities.Compte;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CompteRepository extends JpaRepository<Compte, String> { // interface qui herite d'une interface -----
																			// le ID de compte est de type String (2 eme
																			// parametre)
	@Query("select o from Compte o where o.codeCompte=:x ") //
	public Page<Compte> findAll(@Param("x") String codeCompte, Pageable pageable);
}