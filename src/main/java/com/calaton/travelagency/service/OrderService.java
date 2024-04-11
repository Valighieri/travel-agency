package com.calaton.travelagency.service;

import com.calaton.travelagency.model.domain.Client;
import com.calaton.travelagency.model.domain.Order;
import com.calaton.travelagency.model.domain.Tour;
import com.calaton.travelagency.repository.ClientRepository;
import com.calaton.travelagency.repository.OrderRepository;
import com.calaton.travelagency.repository.TourRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ClientRepository clientRepository;
    private final TourRepository tourRepository;

    public Order getOrderById(Integer id) {
        return orderRepository.findById(id)
                .orElseThrow();
    }

    public Order setOrder(Order order) {
        Integer id = order.getClient().getId();
        if (id != null) {
            Client client = clientRepository.findById(id)
                    .orElseThrow();
            order.setClient(client);
        } else {
            throw new EntityNotFoundException();
        }

        id = order.getTour().getId();
        if (id != null) {
            Tour tour = tourRepository.findById(id)
                    .orElseThrow();
            order.setTour(tour);
        } else {
            throw new EntityNotFoundException();
        }

        return orderRepository.save(order);
    }
}
