package com.calaton.travelagency.controller;

import com.calaton.travelagency.model.dto.OrderDto;
import com.calaton.travelagency.service.OrderService;
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
@RequestMapping("/api/v1/orders")
@AllArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/find/{id}")
    @ResponseStatus(HttpStatus.OK)
    public OrderDto getOrderById(@PathVariable Integer id) {
        return orderService.getOrderById(id);
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.OK)
    public OrderDto setOrder(@RequestBody OrderDto orderDto){
        return orderService.setOrder(orderDto);
    }
}
