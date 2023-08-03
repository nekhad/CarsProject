package com.example.carsproject.controller;

import com.example.carsproject.entity.Car;
import com.example.carsproject.entity.Owner;
import com.example.carsproject.service.inter.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/owners")
public class OwnerController {

    @Autowired
    private OwnerService ownerService;
    @GetMapping("/get")
    public ResponseEntity<List<Owner>> getAllOwners() {
        return ResponseEntity.ok(ownerService.getAllOwners());
    }

    @PostMapping("/add")
    public void addOwner(@RequestBody Owner owner) {
        ownerService.addOwner(owner);
    }

    @PutMapping("/{id}")
    public void updateOwner(@PathVariable Long id, @RequestBody Owner owner) {
        ownerService.updateOwnerDetails(id, owner);
    }

    @DeleteMapping("/{id}")
    public void deleteOwner(@PathVariable Long id) {
        ownerService.deleteOwner(id);
    }
}
