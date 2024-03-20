package com.redoz.onclass.adapters.driving.http.mapper;

import com.redoz.onclass.adapters.driving.http.dto.request.CreateTechnologyRequest;
import com.redoz.onclass.adapters.driving.http.dto.response.FindTechnologyResponse;
import com.redoz.onclass.domain.model.Technology;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ITechnologyRequestMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "capacities", ignore = true)
    Technology createRequestToModel(CreateTechnologyRequest createTechnologyRequest);

    FindTechnologyResponse modelToFindResponse(Technology technology);
}
