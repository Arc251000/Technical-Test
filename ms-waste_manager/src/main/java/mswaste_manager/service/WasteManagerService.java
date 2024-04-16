package mswaste_manager.service;

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
    public WasteManagerDTO getById(int id){
        Optional<WasteManagerEntity> newWasteManager = repository.findById(id);

        if(!newWasteManager.isPresent())
            return null;

        return WASTE_MANAGER_MAPPER.map(newWasteManager.get());
    }

    public Long create(WasteManagerDTO newWasteManager){
        LocalDate now = LocalDate.now();
        newWasteManager.setCreatedDate(now);
        newWasteManager.setLastModifiedDate(now);
        if(newWasteManager.getIsEnabled()==null)
            newWasteManager.setIsEnabled(true);

        WasteManagerEntity wasteManager = repository.save(WASTE_MANAGER_MAPPER.map(newWasteManager));

        if(newWasteManager.getListOfWasteCenterAuthorization()!=null)
            for(WasteCenterAuthorizationEntity w: newWasteManager.getListOfWasteCenterAuthorization()){
                w.setWasteManager(wasteManager);
                wasteCenterAuthorizationrepository.save(w);
            }

        return wasteManager.getId();
    }

    public void update(WasteManagerDTO newWasteManager) throws Exception {
        if(!repository.existsById(newWasteManager.getId()))
            throw new Exception("WasteManager do not exist");

        newWasteManager.setLastModifiedDate(LocalDate.now());

        WasteManagerEntity wasteManager = repository.save(WASTE_MANAGER_MAPPER.map(newWasteManager));

        if(newWasteManager.getListOfWasteCenterAuthorization()!=null) {
            wasteCenterAuthorizationrepository.deleteAll(wasteManager.getListOfWasteCenterAuthorization());
            for (WasteCenterAuthorizationEntity w : newWasteManager.getListOfWasteCenterAuthorization()) {
                w.setWasteManager(wasteManager);
                wasteCenterAuthorizationrepository.save(w);
            }
        }


    }



}
