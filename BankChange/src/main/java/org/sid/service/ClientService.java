package org.sid.service;

import java.util.List;

import org.sid.entities.Client;
import org.springframework.data.domain.Page;

public interface ClientService {
	public List<Client> listClient();

	public Page<Client> listClientPage(int page, int size);

}
