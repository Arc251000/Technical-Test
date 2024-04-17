package mswaste_manager.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;


@Entity
@Data
public class WasteManagerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String nif;
    private Long wasteManagerAddressEntityId;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH, mappedBy= "wasteManager")
    private List<WasteCenterAuthorizationEntity>
            listOfWasteCenterAuthorization;
    private Boolean isEnabled = Boolean.TRUE;
    private Long version = 0L;
    private LocalDate createdDate;
    private LocalDate lastModifiedDate;

}
