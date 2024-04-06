package com.redoz.onclass.adapters.driven.jpa.mysql.mapper;

import com.redoz.onclass.adapters.driven.jpa.mysql.entity.VersionEntity;
import com.redoz.onclass.domain.model.Version;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IVersionEntityMapper {
    VersionEntity toEntity(Version version);

    List<Version> toModelList(List<VersionEntity> versionEntities);
}
