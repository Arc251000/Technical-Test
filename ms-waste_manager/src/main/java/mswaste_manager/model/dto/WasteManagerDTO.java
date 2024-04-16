package mswaste_manager.model.dto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Null;
import lombok.Getter;
import lombok.Setter;
import mswaste_manager.model.entity.WasteCenterAuthorizationEntity;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class WasteManagerDTO {

    private Long id;
    private String name;
    private String nif;
    private List<WasteCenterAuthorizationEntity>
            listOfWasteCenterAuthorization;
    private Boolean isEnabled;
    @Null
    private LocalDate createdDate;
    @Null
    private LocalDate lastModifiedDate;

}
