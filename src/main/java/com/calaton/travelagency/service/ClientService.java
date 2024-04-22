package com.calaton.travelagency.service;

import com.calaton.travelagency.mapper.ClientMapper;
import com.calaton.travelagency.model.domain.Client;
import com.calaton.travelagency.model.dto.ClientDto;
import com.calaton.travelagency.model.exception.ResourceNotFoundException;
import com.calaton.travelagency.repository.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;

    private final ClientMapper clientMapper;

    public ClientDto getClientById(Long id) {
        Client client = clientRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Client not found with id = " + id));
        return clientMapper.toDto(client);
    }

    public ClientDto saveClient(ClientDto clientDto) {
        Client client = clientMapper.toEntity(clientDto);
        client = clientRepository.save(client);
        return clientMapper.toDto(client);
    }

}
