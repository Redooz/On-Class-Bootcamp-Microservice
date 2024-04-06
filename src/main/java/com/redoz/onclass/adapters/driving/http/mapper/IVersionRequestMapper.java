package com.redoz.onclass.adapters.driving.http.mapper;

import com.redoz.onclass.adapters.driving.http.dto.request.CreateVersionRequest;
import com.redoz.onclass.adapters.driving.http.dto.response.FindVersionResponse;
import com.redoz.onclass.domain.model.Version;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IVersionRequestMapper {
    @Mapping(target = "id", ignore = true)
    Version toModel(CreateVersionRequest request);

    FindVersionResponse modelToFindResponse(Version version);

    List<FindVersionResponse> modelToFindResponseList(List<Version> versions);
}
