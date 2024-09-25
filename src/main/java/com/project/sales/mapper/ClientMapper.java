package com.project.sales.mapper;

import com.project.sales.DTO.ClientDTO;
import com.project.sales.DTO.ClientRetrieve;
import com.project.sales.model.Client;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    ClientRetrieve fromEntity(Client client);

    Client fromDTO(ClientDTO clientDTO);
}
