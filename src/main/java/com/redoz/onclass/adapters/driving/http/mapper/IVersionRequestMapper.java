package com.redoz.onclass.adapters.driving.http.mapper;

import com.redoz.onclass.adapters.driving.http.dto.request.CreateVersionRequest;
import com.redoz.onclass.domain.model.Version;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IVersionRequestMapper {
    @Mapping(target = "id", ignore = true)
    Version toModel(CreateVersionRequest request);
}
