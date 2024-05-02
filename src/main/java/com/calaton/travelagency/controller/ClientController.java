package com.calaton.travelagency.controller;

import com.calaton.travelagency.model.dto.BookingTourDto;
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

import java.util.List;

@RestController
@RequestMapping("/api/v1/clients")
@AllArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ClientDto getClientById(@PathVariable Long id) {
        return clientService.getClientById(id);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public ClientDto saveClient(@RequestBody ClientDto clientDto) { // rename create
        return clientService.saveClient(clientDto);
    }

    @PostMapping("/booking")
    @ResponseStatus(HttpStatus.CREATED)
    public ClientDto bookTour(@RequestBody BookingTourDto bookingTourDto) {
        return clientService.bookTour(bookingTourDto);
    }

    @GetMapping("/without-orders/{year}")
    @ResponseStatus(HttpStatus.OK)
    public List<ClientDto> getClientsWithoutOrders(@PathVariable Integer year) {
        return clientService.getClientsWithoutOrders(year);
    }

    @GetMapping("/highest/discount")
    @ResponseStatus(HttpStatus.OK)
    public List<ClientDto> getClientsWithTheHighestDiscount() {
        return clientService.getClientsWithTheHighestDiscount();
    }

    @GetMapping("/highest/revenue")
    @ResponseStatus(HttpStatus.OK)
    public ClientDto getClientWithTheHighestRevenueOverall() {
        return clientService.getClientWithTheHighestRevenueOverall();
    }

}
