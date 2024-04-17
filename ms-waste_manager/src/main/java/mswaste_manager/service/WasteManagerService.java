package mswaste_manager.service;

import mswaste_manager.client.WasteManagerAddressClient;
import mswaste_manager.model.dto.WasteManagerAddressDTO;
import mswaste_manager.model.dto.WasteManagerDTO;
import mswaste_manager.model.entity.WasteCenterAuthorizationEntity;
import mswaste_manager.model.entity.WasteManagerEntity;
import mswaste_manager.repository.WasteCenterAuthorizationJPARepository;
import mswaste_manager.repository.WasteManagerJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

import static mswaste_manager.model.mapper.WasteManagerMapper.WASTE_MANAGER_MAPPER;

@Service
public class WasteManagerService {

    @Autowired
    WasteManagerJPARepository repository;
    @Autowired
    WasteCenterAuthorizationJPARepository wasteCenterAuthorizationrepository;
    @Autowired
    WasteManagerAddressClient client;
    public WasteManagerDTO getById(int id){
        Optional<WasteManagerEntity> newWasteManager = repository.findById(id);

        if(!newWasteManager.isPresent())
            return null;

        WasteManagerDTO wasteManager = WASTE_MANAGER_MAPPER.map(newWasteManager.get());
        if(newWasteManager.get().getWasteManagerAddressEntityId()!=null)
            wasteManager.setWasteManagerAddress(client.getById(newWasteManager.get().getWasteManagerAddressEntityId()).getBody().getAddress());

        return wasteManager ;
    }

    public Long create(WasteManagerDTO newWasteManager){
        LocalDate now = LocalDate.now();
        newWasteManager.setCreatedDate(now);
        newWasteManager.setLastModifiedDate(now);
        if(newWasteManager.getIsEnabled()==null)
            newWasteManager.setIsEnabled(true);

        WasteManagerEntity wasteManager = WASTE_MANAGER_MAPPER.map(newWasteManager);
        if(newWasteManager.getWasteManagerAddress()!=null)
            wasteManager.setWasteManagerAddressEntityId(client.create(WasteManagerAddressDTO.builder().address(newWasteManager.getWasteManagerAddress()).build()).getBody());

        repository.save(wasteManager);

        if(newWasteManager.getListOfWasteCenterAuthorization()!=null)
            for(WasteCenterAuthorizationEntity w: newWasteManager.getListOfWasteCenterAuthorization()){
                w.setWasteManager(wasteManager);
                wasteCenterAuthorizationrepository.save(w);
            }

        return wasteManager.getId();
    }

    public void update(WasteManagerDTO newWasteManager) throws Exception {
        Optional<WasteManagerEntity> old = repository.findById(newWasteManager.getId());
        if(!old.isPresent())
            throw new Exception("WasteManager do not exist");

        newWasteManager.setLastModifiedDate(LocalDate.now());

        WasteManagerEntity wasteManager = WASTE_MANAGER_MAPPER.map(newWasteManager);
        if(newWasteManager.getWasteManagerAddress()!=null && old.get().getWasteManagerAddressEntityId()==null)
            wasteManager.setWasteManagerAddressEntityId(client.create(WasteManagerAddressDTO.builder().address(newWasteManager.getWasteManagerAddress()).build()).getBody());
        else if(newWasteManager.getWasteManagerAddress()!=null) {
            client.update(WasteManagerAddressDTO.builder().id(old.get().getWasteManagerAddressEntityId()).address(newWasteManager.getWasteManagerAddress()).build());
        }
        repository.save(wasteManager);

        if(newWasteManager.getListOfWasteCenterAuthorization()!=null) {
            wasteCenterAuthorizationrepository.deleteAll(wasteManager.getListOfWasteCenterAuthorization());
            for (WasteCenterAuthorizationEntity w : newWasteManager.getListOfWasteCenterAuthorization()) {
                w.setWasteManager(wasteManager);
                wasteCenterAuthorizationrepository.save(w);
            }
        }


    }



}
