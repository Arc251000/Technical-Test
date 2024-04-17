package com.example.mswastemanageraddress.model.mapper;

import com.example.mswastemanageraddress.model.dto.WasteManagerAddressDTO;
import com.example.mswastemanageraddress.model.entity.WasteManagerAddressEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface WasteManagerAddressMapper {

    WasteManagerAddressMapper WASTE_MANAGER_ADDRESS_MAPPER = Mappers.getMapper(WasteManagerAddressMapper.class);
    WasteManagerAddressDTO map(WasteManagerAddressEntity source);

    WasteManagerAddressEntity map(WasteManagerAddressDTO source);

}
