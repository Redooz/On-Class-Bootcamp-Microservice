package com.redoz.onclassbootcamp.adapters.driving.http.mapper;

import com.redoz.onclassbootcamp.adapters.driving.http.dto.request.CreateCapacityRequest;
import com.redoz.onclassbootcamp.adapters.driving.http.dto.response.FindCapacityResponse;
import com.redoz.onclassbootcamp.domain.model.Capacity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ICapacityRequestMapper {
    @Mapping(target = "id", ignore = true)
    Capacity toModel(CreateCapacityRequest createCapacityRequest);

    FindCapacityResponse modelToFindResponse(Capacity capacity);
}
