package com.project.sales.services;

import com.project.sales.DTO.ClientDTO;
import com.project.sales.DTO.ClientRetrieve;

import java.util.List;

public interface ClientService {
    ClientRetrieve create(ClientDTO entity);

    ClientRetrieve findByCpf(String cpf);

    ClientRetrieve findById(Long id);

    List<ClientRetrieve> findAll();

    ClientRetrieve update(Long id, ClientDTO clientDTO);

    void delete(Long id);
}
