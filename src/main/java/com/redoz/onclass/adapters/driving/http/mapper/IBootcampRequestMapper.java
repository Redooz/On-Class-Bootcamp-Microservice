package com.redoz.onclass.adapters.driving.http.mapper;

import com.redoz.onclass.adapters.driving.http.dto.request.CreateBootcampRequest;
import com.redoz.onclass.adapters.driving.http.dto.response.FindBootcampResponse;
import com.redoz.onclass.domain.model.Bootcamp;
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
