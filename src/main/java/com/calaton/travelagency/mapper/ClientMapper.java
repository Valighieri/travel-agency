package com.calaton.travelagency.mapper;

import com.calaton.travelagency.model.domain.Client;
import com.calaton.travelagency.model.dto.ClientDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    ClientDto toClientDto(Client client);

    Client toClient(ClientDto clientDto);
}
