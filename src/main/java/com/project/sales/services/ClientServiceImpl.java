package com.project.sales.services;

import com.project.sales.DTO.AddressDTO;
import com.project.sales.DTO.ClientDTO;
import com.project.sales.DTO.ClientRetrieve;
import com.project.sales.mapper.ClientMapper;
import com.project.sales.model.Address;
import com.project.sales.model.Client;
import com.project.sales.model.Sale;
import com.project.sales.model.StatusSale;
import com.project.sales.repository.AddressRepository;
import com.project.sales.repository.ClientRepository;
import com.project.sales.repository.SaleRepository;
import com.project.sales.utilitis.exception.AlreadyExistsException;
import com.project.sales.utilitis.exception.BadRequestException;
import com.project.sales.utilitis.exception.NotFoundException;
import com.project.sales.utilitis.utils.Utils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    private final ClientMapper clientMapper;

    private final AddressService addressService;

    private final MessageSource messageSource;

    private final AddressRepository addressRepository;

    private final SaleRepository saleRepository;

    @Override
    @Transactional
    public ClientRetrieve create(ClientDTO clientDTO) {
        clientDTO.setCpf(Utils.removeMask(clientDTO.getCpf()));
        clientDTO.setPhone(Utils.removeMask(clientDTO.getPhone()));

        if(clientRepository.findByCpf(clientDTO.getCpf()).isPresent()) {
            throw new AlreadyExistsException(messageSource.getMessage("service.client.cpf.already.exists", null, Locale.getDefault()));
        }

        Client client = clientMapper.fromDTO(clientDTO);

        if (clientDTO.getAddress() != null) {
            AddressDTO address = clientDTO.getAddress();
            address.setPostalCode(Utils.removeMask(address.getPostalCode()));
            Address addressBuilt = addressService.buildAddress(address)
                    .orElse(null);
            client.setAddress(addressBuilt);
        }

        client = clientRepository.save(client);
        log.info("Cliente salvo com sucesso!");

        return clientMapper.fromEntity(client);
    }

    @Override
    @Transactional(readOnly = true)
    public ClientRetrieve findByCpf(String cpf) {
        cpf = Utils.removeMask(cpf);

        Client client = clientRepository.findByCpf(cpf)
                .orElseThrow(() -> new NotFoundException(messageSource.getMessage("service.client.not.found.by.cpf", null, Locale.getDefault())));

        return clientMapper.fromEntity(client);
    }

    @Override
    @Transactional(readOnly = true)
    public ClientRetrieve findById(Long id) {
        Client client = clientRepository.findById(id).orElseThrow(() -> new NotFoundException(messageSource.getMessage("service.client.not.found.by.id", null, Locale.getDefault())));

        return clientMapper.fromEntity(client);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ClientRetrieve> findAll() {
        List<Client> clientList = clientRepository.findAll();

        if (clientList.isEmpty()) {
            throw new BadRequestException(messageSource.getMessage("service.client.list.empty", null, Locale.getDefault()));
        }

        return clientList.stream().map(clientMapper::fromEntity).collect(Collectors.toList());
    }


    @Override
    @Transactional
    public ClientRetrieve update(Long id, ClientDTO clientDTO) {
        clientDTO.setCpf(Utils.removeMask(clientDTO.getCpf()));
        clientDTO.setPhone(Utils.removeMask(clientDTO.getPhone()));

        Client persistedClient = clientRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(messageSource.getMessage("service.client.not.found.by.id", null, Locale.getDefault())));

        Address persistedAddress = addressRepository.findById(persistedClient.getAddress().getId())
                .orElseThrow(() -> new NotFoundException(messageSource.getMessage("service.address.not.found.by.id", null, Locale.getDefault())));

        Client updatedClient = clientMapper.fromDTO(clientDTO);

        updateAddress(clientDTO.getAddress(), persistedAddress);

        updateClient(updatedClient, persistedClient);

        persistedClient.setAddress(persistedAddress);

        persistedClient = clientRepository.save(persistedClient);

        return clientMapper.fromEntity(persistedClient);
    }

    private void updateAddress (AddressDTO updatedAddress, Address persistedAddress) {
        persistedAddress.setStreet(updatedAddress.getStreet());
        persistedAddress.setNumber(updatedAddress.getNumber());
        persistedAddress.setNeighborhood(updatedAddress.getNeighborhood());
        persistedAddress.setCity(updatedAddress.getCity());
        persistedAddress.setState(updatedAddress.getState());
        persistedAddress.setCountry(updatedAddress.getCountry());
        persistedAddress.setPostalCode(updatedAddress.getPostalCode());
        addressRepository.save(persistedAddress);
    }

    private void updateClient(Client updatedClient, Client persistedClient) {
        persistedClient.setCpf(updatedClient.getCpf());

        persistedClient.setName(updatedClient.getName());

        persistedClient.setEmail(updatedClient.getEmail());

        persistedClient.setPhone(updatedClient.getPhone());

        persistedClient.setAddress(updatedClient.getAddress());

        persistedClient.setDateOfBirth(updatedClient.getDateOfBirth());

        persistedClient.setDeleted(updatedClient.isDeleted());
    }

    @Override
    @Transactional(rollbackFor = Error.class)
    public void delete(Long id) {
        Client persistedClient = clientRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(messageSource.getMessage("service.client.not.found.by.id", null, Locale.getDefault())));

        if(persistedClient.isDeleted()) throw new BadRequestException(messageSource.getMessage("service.client.is.deleted", null, Locale.getDefault()));

        if(searchClientInSale(persistedClient.getId())) {
            persistedClient.setDeleted(true);
            clientRepository.save(persistedClient);
            log.info("Cliente desativado com sucesso!");
        } else {
            throw new BadRequestException(messageSource.getMessage("service.client.in.sale.not.delete", null, Locale.getDefault()));
        }
    }

    private boolean searchClientInSale(Long id) {
        List<Sale> listClient = saleRepository.findByClientId(id);
        if(listClient.isEmpty()) return true;

        for (Sale sale : listClient) {
            if (sale.getStatus().equals(StatusSale.PENDENTE)) return false;
        }

        return true;
    }
}
