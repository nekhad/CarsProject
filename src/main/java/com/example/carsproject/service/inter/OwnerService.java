package com.example.carsproject.service.inter;

import com.example.carsproject.entity.Car;
import com.example.carsproject.entity.Owner;

import java.util.List;

public interface OwnerService {
    void addOwner(Owner owner);

    List<Owner> getAllOwners();
    void updateOwnerDetails(Long id, Owner owner);

    void deleteOwner(Long id);
}
