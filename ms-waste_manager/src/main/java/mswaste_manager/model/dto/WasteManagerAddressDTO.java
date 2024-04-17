package mswaste_manager.model.dto;

import jakarta.validation.constraints.Null;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class WasteManagerAddressDTO {

    private Long id;
    private String address;
    private Boolean isEnabled;
    private LocalDate createdDate;
    private LocalDate lastModifiedDate;

}
