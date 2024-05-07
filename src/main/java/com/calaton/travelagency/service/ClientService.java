package com.calaton.travelagency.service;

import com.calaton.travelagency.mapper.ClientMapper;
import com.calaton.travelagency.model.domain.Client;
import com.calaton.travelagency.model.domain.Tour;
import com.calaton.travelagency.model.dto.BookingTourDto;
import com.calaton.travelagency.model.dto.ClientDto;
import com.calaton.travelagency.model.exception.InvalidDataException;
import com.calaton.travelagency.model.exception.ResourceNotFoundException;
import com.calaton.travelagency.repository.ClientRepository;
import com.calaton.travelagency.repository.TourRepository;
import com.calaton.travelagency.util.TourAnalyzer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@AllArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;
    private final TourRepository tourRepository;

    private final TourAnalyzer tourAnalyzer;

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

    public ClientDto bookTour(BookingTourDto bookingTourDto) {
        Long clientId = bookingTourDto.clientId();
        Long tourId = bookingTourDto.tourId();
        BigDecimal discount = bookingTourDto.discount();
        if (clientId == null || tourId == null) {
            throw new InvalidDataException("Client id and tour id must not be null");
        }

        Client client = clientRepository.findById(clientId).orElseThrow(
                () -> new ResourceNotFoundException("Client not found with id = " + clientId));
        Tour tour = tourRepository.findById(tourId).orElseThrow(
                () -> new ResourceNotFoundException("Tour not found with id = " + tourId));

        List<Tour> clientBookedTours = clientRepository.findClientBookedTours(clientId);
        tourAnalyzer.checkIfTheTourPeriodIsAvailable(clientBookedTours, tour);

        client.getTours().add(tour);
        client.getDiscounts().put(tourId, discount);
        client = clientRepository.save(client);
        return clientMapper.toDto(client);
    }

    public List<ClientDto> getClientsWithoutOrders(Integer year) {
        List<Client> clients = clientRepository.findClientsWithoutOrders(year);
        return clientMapper.toDtoList(clients);
    }

    public List<ClientDto> getClientsWithTheHighestDiscount() {
        List<Client> clients = clientRepository.findClientsWithTheHighestDiscount();
        return clientMapper.toDtoList(clients);
    }

    public ClientDto getClientWithTheHighestRevenueOverall() {
        Client client = clientRepository.findClientWithTheHighestRevenueOverall().orElseThrow(
                () -> new ResourceNotFoundException("Client not found"));
        return clientMapper.toDto(client);
    }

}
