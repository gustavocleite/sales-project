package com.project.sales.mapper;

import com.project.sales.DTO.AddressDTO;
import com.project.sales.DTO.AddressRetrieve;
import com.project.sales.model.Address;
import jakarta.validation.constraints.NotNull;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressMapper {
    AddressRetrieve fromEntity(Address address);

    Address fromDTO(AddressDTO address);
}
