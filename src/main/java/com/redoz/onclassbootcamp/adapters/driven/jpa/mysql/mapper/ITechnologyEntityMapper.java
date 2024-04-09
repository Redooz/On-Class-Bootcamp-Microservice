package com.redoz.onclassbootcamp.adapters.driven.jpa.mysql.mapper;

import com.redoz.onclassbootcamp.adapters.driven.jpa.mysql.entity.TechnologyEntity;
import com.redoz.onclassbootcamp.domain.model.Technology;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ITechnologyEntityMapper {
    TechnologyEntity toEntity(Technology technology);
    Technology toModel(TechnologyEntity technologyEntity);

    @Mapping(target = "capacities", ignore = true)
    List<Technology> toModelList(List<TechnologyEntity> technologyEntities);
}
