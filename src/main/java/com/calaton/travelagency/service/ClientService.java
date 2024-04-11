package com.calaton.travelagency.service;

import com.calaton.travelagency.model.domain.Client;
import com.calaton.travelagency.repository.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;

    public Client getClientById(Integer id) {
        return clientRepository.findById(id)
                .orElseThrow();
    }

    public Client setClient(Client client) {
        return clientRepository.save(client);
    }
}
