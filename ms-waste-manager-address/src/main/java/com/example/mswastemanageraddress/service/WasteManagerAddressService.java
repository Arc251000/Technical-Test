package com.example.mswastemanageraddress.service;

import com.example.mswastemanageraddress.model.dto.WasteManagerAddressDTO;
import com.example.mswastemanageraddress.model.entity.WasteManagerAddressEntity;
import com.example.mswastemanageraddress.repository.WasteManagerAddressJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

import static com.example.mswastemanageraddress.model.mapper.WasteManagerAddressMapper.WASTE_MANAGER_ADDRESS_MAPPER;

@Service
public class WasteManagerAddressService {

    @Autowired
    WasteManagerAddressJPARepository repository;

    public WasteManagerAddressDTO getById(int id){
        Optional<WasteManagerAddressEntity> newWasteManager = repository.findById(id);

        if(!newWasteManager.isPresent())
            return null;

        return WASTE_MANAGER_ADDRESS_MAPPER.map(newWasteManager.get());
    }

    public Long create(WasteManagerAddressDTO newWasteManager){
        LocalDate now = LocalDate.now();
        newWasteManager.setCreatedDate(now);
        newWasteManager.setLastModifiedDate(now);
        if(newWasteManager.getIsEnabled()==null)
            newWasteManager.setIsEnabled(true);

        return repository.save(WASTE_MANAGER_ADDRESS_MAPPER.map(newWasteManager)).getId();
    }

    public void update(WasteManagerAddressDTO newWasteManagerAddress) throws Exception {
        if(!repository.existsById(newWasteManagerAddress.getId()))
            throw new Exception("WasteManager do not exist");

        newWasteManagerAddress.setLastModifiedDate(LocalDate.now());

        repository.save(WASTE_MANAGER_ADDRESS_MAPPER.map(newWasteManagerAddress));
    }

}
