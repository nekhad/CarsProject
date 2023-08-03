package com.example.carsproject.service.impl;

import com.example.carsproject.entity.Owner;
import com.example.carsproject.repository.OwnerRepository;
import com.example.carsproject.service.inter.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OwnerServiceImpl implements OwnerService {
    @Autowired
    private OwnerRepository ownerRepository;

    @Override
    public void addOwner(Owner owner) {
        ownerRepository.save(owner);
    }

    @Override
    public List<Owner> getAllOwners() {
        return ownerRepository.findAll();
    }

    @Override
    public void updateOwnerDetails(Long id, Owner owner) {
        Optional<Owner> existingOwner = ownerRepository.findById(id);
        if (existingOwner.isPresent()) {
            owner.setId(id);
            owner.setName(owner.getName());
            owner.setSurname(owner.getSurname());
            owner.setBirthDate(owner.getBirthDate());
            owner.setFatherName(owner.getFatherName());
            owner.setTelephoneNumber(owner.getTelephoneNumber());
        } else {
            throw new RuntimeException("Owner is not found");
        }
    }

    @Override
    public void deleteOwner(Long id) {
        ownerRepository.deleteById(id);
    }
}
