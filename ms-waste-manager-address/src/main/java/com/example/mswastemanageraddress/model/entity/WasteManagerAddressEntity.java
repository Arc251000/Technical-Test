package com.example.mswastemanageraddress.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class WasteManagerAddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String address;
    private Boolean isEnabled = Boolean.TRUE;
    private Long version = 0L;
    private LocalDate createdDate;
    private LocalDate lastModifiedDate;

}
