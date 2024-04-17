package com.example.mswastemanageraddress.controller;

import com.example.mswastemanageraddress.model.dto.WasteManagerAddressDTO;
import com.example.mswastemanageraddress.model.entity.WasteManagerAddressEntity;
import com.example.mswastemanageraddress.service.WasteManagerAddressService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;

@RestController
@RequestMapping("waste-manager-address")
public class WasteManagerAddressController{
    @Autowired
    WasteManagerAddressService service;


    @GetMapping("/{id}")
    public ResponseEntity<WasteManagerAddressDTO> getById(@PathVariable(name = "id") int id){

        return ResponseEntity.ok(service.getById(id));

    }

    @PostMapping("/create")
    public ResponseEntity<Long> create(@Valid @RequestBody WasteManagerAddressDTO wasteManagerAddressDto,
                                       BindingResult bindingResult ) throws ResponseStatusException {

        if(bindingResult.hasErrors()||wasteManagerAddressDto.getId()!=null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"");

        return ResponseEntity.ok(service.create(wasteManagerAddressDto));

    }

    @PutMapping("/update")
    public ResponseEntity update(@Valid @RequestBody WasteManagerAddressDTO wasteManagerDto,
                                 BindingResult bindingResult) throws ResponseStatusException{

        if(bindingResult.hasErrors()||wasteManagerDto.getId()==null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"");

        try {
            service.update(wasteManagerDto);
            return ResponseEntity.noContent().build();
        }catch (Exception e){
            System.out.println(e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"");
        }

    }


}
