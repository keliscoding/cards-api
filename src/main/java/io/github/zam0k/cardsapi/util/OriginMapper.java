package io.github.zam0k.cardsapi.util;

import io.github.zam0k.cardsapi.controller.dto.origin.OriginRequest;
import io.github.zam0k.cardsapi.controller.dto.origin.OriginResponse;
import io.github.zam0k.cardsapi.controller.dto.origin.OriginUpdateRequest;
import io.github.zam0k.cardsapi.model.Origin;
import org.mapstruct.Condition;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface OriginMapper {
    OriginMapper INSTANCE = Mappers.getMapper(OriginMapper.class);

    Origin originRequestToOrigin(OriginRequest originRequest);

    OriginResponse originToOriginResponse(Origin origin);

    List<OriginResponse> originListToOriginResponseList(List<Origin> origins);

    Origin originUpdateRequestToOrigin(@MappingTarget Origin origin, OriginUpdateRequest originUpdateRequest);

    @Condition
    default boolean isNotEmpty(String value) {
        return value != null && !value.trim().isEmpty();
    }
}
