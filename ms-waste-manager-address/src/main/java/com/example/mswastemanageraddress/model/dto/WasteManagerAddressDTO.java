package com.example.mswastemanageraddress.model.dto;

import jakarta.validation.constraints.Null;
import lombok.Data;

import java.time.LocalDate;

@Data
public class WasteManagerAddressDTO {

    private Long id;
    private String address;
    private Boolean isEnabled;
    @Null
    private LocalDate createdDate;
    @Null
    private LocalDate lastModifiedDate;

}
