package com.calaton.travelagency.service;

import com.calaton.travelagency.model.domain.Order;
import com.calaton.travelagency.repository.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public Order getOrderById(int id) {
        return orderRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }
}
