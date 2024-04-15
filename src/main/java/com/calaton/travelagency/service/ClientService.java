package com.calaton.travelagency.service;

import com.calaton.travelagency.mapper.ClientMapper;
import com.calaton.travelagency.model.domain.Client;
import com.calaton.travelagency.model.dto.ClientDto;
import com.calaton.travelagency.repository.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    public ClientDto getClientById(Integer id) {
        Client client = clientRepository.findById(id)
                .orElseThrow();
        return clientMapper.toClientDto(client);
    }

    public ClientDto setClient(ClientDto clientDto) {
        Client client = clientMapper.toClient(clientDto);
        Client response = clientRepository.save(client);
        return clientMapper.toClientDto(response);
    }
}
