package com.calaton.travelagency.mapper;

import com.calaton.travelagency.model.domain.Client;
import com.calaton.travelagency.model.dto.ClientDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = TourMapper.class)
public interface ClientMapper {

    @Mapping(source = "tours", target = "tourDtoList")
    ClientDto toDto(Client client);

    Client toEntity(ClientDto clientDto);

    List<ClientDto> toDtoList(List<Client> clients);

}
