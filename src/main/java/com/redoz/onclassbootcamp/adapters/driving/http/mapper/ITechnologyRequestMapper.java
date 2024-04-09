package com.redoz.onclassbootcamp.adapters.driving.http.mapper;

import com.redoz.onclassbootcamp.adapters.driving.http.dto.request.CreateTechnologyRequest;
import com.redoz.onclassbootcamp.adapters.driving.http.dto.response.FindTechnologyResponse;
import com.redoz.onclassbootcamp.domain.model.Technology;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ITechnologyRequestMapper {
    @Mapping(target = "id", ignore = true)
    Technology createRequestToModel(CreateTechnologyRequest createTechnologyRequest);

    FindTechnologyResponse modelToFindResponse(Technology technology);
}
