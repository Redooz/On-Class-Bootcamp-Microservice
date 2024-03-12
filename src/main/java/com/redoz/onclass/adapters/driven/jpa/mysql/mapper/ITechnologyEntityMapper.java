package com.redoz.onclass.adapters.driven.jpa.mysql.mapper;

import com.redoz.onclass.adapters.driven.jpa.mysql.entity.TechnologyEntity;
import com.redoz.onclass.domain.model.Technology;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ITechnologyEntityMapper {
    TechnologyEntity toEntity(Technology technology);
    Technology toModel(TechnologyEntity technologyEntity);
    List<Technology> toModelList(List<TechnologyEntity> technologyEntities);
}
