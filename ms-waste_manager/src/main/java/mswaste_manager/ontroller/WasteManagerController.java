package mswaste_manager.ontroller;

import jakarta.validation.Valid;
import mswaste_manager.model.dto.WasteManagerDTO;
import mswaste_manager.model.entity.WasteManagerEntity;
import mswaste_manager.model.mapper.WasteManagerMapper;
import mswaste_manager.repository.WasteManagerJPARepository;
import mswaste_manager.service.WasteManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import static mswaste_manager.model.mapper.WasteManagerMapper.WASTE_MANAGER_MAPPER;

@RestController
@RequestMapping("/waste-manager")
public class WasteManagerController {

    @Autowired
    WasteManagerService service;


    @GetMapping("/{id}")
    public ResponseEntity<WasteManagerDTO> getById(@PathVariable(name = "id") int id){

        return ResponseEntity.ok(service.getById(id));

    }

    @PostMapping("/create")
    public ResponseEntity<Long> create(@Valid @RequestBody WasteManagerDTO wasteManagerDto,
                                           BindingResult bindingResult ) throws ResponseStatusException{

        if(bindingResult.hasErrors()||wasteManagerDto.getId()!=null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"");

        return ResponseEntity.ok(service.create(wasteManagerDto));

    }

    @PutMapping("/update")
    public ResponseEntity update(@Valid @RequestBody WasteManagerDTO wasteManagerDto,
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
