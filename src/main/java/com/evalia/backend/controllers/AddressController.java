package com.evalia.backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.evalia.backend.models.Address;
import com.evalia.backend.services.AddressService;
@RestController
@RequestMapping("/addresses")
public class AddressController {

    private final AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping
    public Address createAddress(@RequestBody Address country) {
        return addressService.createAddress(country);
    }

    @GetMapping
    public List<Address> getAllAddress() {
        return addressService.getAllAddresses();
    }

    @GetMapping("/{id}")
    public Address getCatalogById(@PathVariable Long id) {
        return addressService.getAddressById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteAddressc(@PathVariable Long id) {
        addressService.deleteAddress(id);
    }
}