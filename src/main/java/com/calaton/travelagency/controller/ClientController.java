package com.calaton.travelagency.controller;

import com.calaton.travelagency.model.dto.ClientDto;
import com.calaton.travelagency.service.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/clients")
@AllArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @GetMapping("/find/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ClientDto getClientById(@PathVariable Integer id) {
        return clientService.getClientById(id);
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.OK)
    public ClientDto setClient(@RequestBody ClientDto clientDto) {
        return clientService.setClient(clientDto);
    }

}
