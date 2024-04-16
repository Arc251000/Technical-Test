package mswaste_manager.model.mapper;

import mswaste_manager.model.dto.WasteManagerDTO;
import mswaste_manager.model.entity.WasteManagerEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Mapper
public interface WasteManagerMapper {
    WasteManagerMapper WASTE_MANAGER_MAPPER = Mappers.getMapper(WasteManagerMapper.class);
    WasteManagerDTO map(WasteManagerEntity source);

    WasteManagerEntity map(WasteManagerDTO source);
}
