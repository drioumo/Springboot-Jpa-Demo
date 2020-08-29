package org.sid.service.implement;

import java.util.List;

import org.sid.dao.ClientRepository;
import org.sid.entities.Client;
import org.sid.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ClientServiceImp implements ClientService {
	@Autowired
	private ClientRepository clientRepository;

	@Override
	public List<Client> listClient() {
		// TODO Auto-generated method stub
		return (List) clientRepository.findAll();
	}

	@Override
	public Page<Client> listClientPage(int page, int size) {
		return clientRepository.listClientRep(new PageRequest(page, size));
	}

}
