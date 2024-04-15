package com.calaton.travelagency.mapper;

import com.calaton.travelagency.model.domain.Order;
import com.calaton.travelagency.model.dto.OrderDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    OrderDto toOrderDto(Order order);

    Order toOrder(OrderDto orderDto);
}
