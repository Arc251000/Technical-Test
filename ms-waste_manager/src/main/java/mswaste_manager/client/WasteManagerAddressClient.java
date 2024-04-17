package mswaste_manager.client;

import jakarta.validation.Valid;
import mswaste_manager.model.dto.WasteManagerAddressDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@FeignClient( name="ms-waste-manager-address", url = "http://localhost:8083")
public interface WasteManagerAddressClient {
    @GetMapping("/waste-manager-address/{id}")
    public ResponseEntity<WasteManagerAddressDTO> getById(@PathVariable(name = "id") long id);

    @PostMapping("/waste-manager-address/create")
    public ResponseEntity<Long> create(@Valid @RequestBody WasteManagerAddressDTO wasteManagerAddressDto);

    @PutMapping("/waste-manager-address/update")
    public ResponseEntity update(@Valid @RequestBody WasteManagerAddressDTO wasteManagerDto);

}
