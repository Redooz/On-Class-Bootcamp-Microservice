package com.redoz.onclassbootcamp.adapters.driven.jpa.mysql.mapper;

import com.redoz.onclassbootcamp.adapters.driven.jpa.mysql.entity.CapacityEntity;
import com.redoz.onclassbootcamp.domain.model.Capacity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ICapacityEntityMapper {
    CapacityEntity toEntity(Capacity capacity);

    Capacity toModel(CapacityEntity capacityEntity);
    List<Capacity> toModelList(List<CapacityEntity> capacityEntities);
}
