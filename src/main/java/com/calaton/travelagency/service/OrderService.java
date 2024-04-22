package com.calaton.travelagency.service;

import com.calaton.travelagency.mapper.OrderMapper;
import com.calaton.travelagency.model.domain.Client;
import com.calaton.travelagency.model.domain.Order;
import com.calaton.travelagency.model.domain.Tour;
import com.calaton.travelagency.model.dto.OrderDto;
import com.calaton.travelagency.model.exception.InvalidDataException;
import com.calaton.travelagency.model.exception.ResourceNotFoundException;
import com.calaton.travelagency.repository.ClientRepository;
import com.calaton.travelagency.repository.OrderRepository;
import com.calaton.travelagency.repository.TourRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ClientRepository clientRepository;
    private final TourRepository tourRepository;

    private final OrderMapper orderMapper;

    public OrderDto getOrderById(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Order not found with id = " + id));
        return orderMapper.toDto(order);
    }

    public OrderDto saveOrder(OrderDto orderDto) {
        Order order = orderMapper.toEntity(orderDto);
        Long clientId = order.getClient().getId();
        Long tourId = order.getTour().getId();
        if (clientId == null || tourId == null) {
            throw new InvalidDataException("Client id and tour id must not be null");
        }

        Client client = clientRepository.findById(clientId).orElseThrow(
                () -> new ResourceNotFoundException("Client not found with id = " + clientId));
        Tour tour = tourRepository.findById(tourId).orElseThrow(
                () -> new ResourceNotFoundException("Tour not found with id = " + tourId));

        order.setClient(client);
        order.setTour(tour);

        order = orderRepository.save(order);
        return orderMapper.toDto(order);
    }

}
