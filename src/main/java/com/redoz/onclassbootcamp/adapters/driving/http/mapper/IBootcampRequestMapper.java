package com.redoz.onclassbootcamp.adapters.driving.http.mapper;

import com.redoz.onclassbootcamp.adapters.driving.http.dto.request.CreateBootcampRequest;
import com.redoz.onclassbootcamp.adapters.driving.http.dto.response.FindBootcampResponse;
import com.redoz.onclassbootcamp.domain.model.Bootcamp;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IBootcampRequestMapper {
    @Mapping(target = "id", ignore = true)
    Bootcamp toModel(CreateBootcampRequest request);

    FindBootcampResponse modelToFindResponse(Bootcamp bootcamp);

    List<FindBootcampResponse> modelToFindResponseList(List<Bootcamp> bootcamps);
}
