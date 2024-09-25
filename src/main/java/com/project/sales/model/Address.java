package com.project.sales.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "address")
public class Address {
    @Id
    @SequenceGenerator(name = "address_id_seq", sequenceName = "address_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "address_id_seq")
    private Long id;

    private String street;
    private String number;
    private String complement;
    private String neighborhood;
    private String city;
    private String state;
    private String country;
    private String postalCode;

    public Address(Address address) {
        address.setStreet(address.getStreet());
        address.setNumber(address.getNumber());
        address.setComplement(address.getComplement());
        address.setNeighborhood(address.getNeighborhood());
        address.setCity(address.getCity());
        address.setState(address.getState());
        address.setCountry(address.getCountry());
        address.setPostalCode(address.getPostalCode());
    }
}
