package com.redoz.onclass.adapters.driven.jpa.mysql.mapper;

import com.redoz.onclass.adapters.driven.jpa.mysql.entity.CapacityEntity;
import com.redoz.onclass.domain.model.Capacity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ICapacityEntityMapper {
    CapacityEntity toEntity(Capacity capacity);
    Capacity toModel(CapacityEntity capacityEntity);
}
