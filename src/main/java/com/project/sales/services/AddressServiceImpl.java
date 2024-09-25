package com.project.sales.services;

import com.project.sales.DTO.AddressDTO;
import com.project.sales.mapper.AddressMapper;
import com.project.sales.model.Address;
import com.project.sales.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService{

    private final AddressRepository addressRepository;

    private final AddressMapper addressMapper;


    @Override
    public Optional<Address> buildAddress(AddressDTO address) {
        Address newAddress = addressMapper.fromDTO(address);

        return Optional.of(addressRepository.save(newAddress));
    }
}
