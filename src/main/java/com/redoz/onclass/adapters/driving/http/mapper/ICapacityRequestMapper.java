package com.redoz.onclass.adapters.driving.http.mapper;

import com.redoz.onclass.adapters.driving.http.dto.request.CreateCapacityRequest;
import com.redoz.onclass.domain.model.Capacity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ICapacityRequestMapper {
    @Mapping(target = "id", ignore = true)
    CreateCapacityRequest toRequest(Capacity capacity);

    Capacity toModel(CreateCapacityRequest createCapacityRequest);
}
