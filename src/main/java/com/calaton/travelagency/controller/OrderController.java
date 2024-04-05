package com.calaton.travelagency.controller;

import com.calaton.travelagency.model.domain.Order;
import com.calaton.travelagency.service.OrderService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/orders")
@AllArgsConstructor
@Slf4j
public class OrderController {

    private final OrderService orderService;

    @GetMapping("find/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Order getOrderById(@PathVariable int id) {
        return orderService.getOrderById(id);
    }

}
