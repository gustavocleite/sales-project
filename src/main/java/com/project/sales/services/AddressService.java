package com.project.sales.services;

import com.project.sales.DTO.AddressDTO;
import com.project.sales.model.Address;

import java.util.Optional;

public interface AddressService {
    Optional<Address> buildAddress(AddressDTO address);
}
