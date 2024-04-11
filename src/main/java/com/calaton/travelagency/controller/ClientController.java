package com.calaton.travelagency.controller;

import com.calaton.travelagency.model.domain.Client;
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

    //TODO: when we send client with id that is present in DB
    // we change row with that id, so we must use DTO ...

    private final ClientService clientService;

    @GetMapping("/find/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Client getClientById(@PathVariable Integer id) {
        return clientService.getClientById(id);
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.OK)
    public Client setClient(@RequestBody Client client) {
        return clientService.setClient(client);
    }

}
