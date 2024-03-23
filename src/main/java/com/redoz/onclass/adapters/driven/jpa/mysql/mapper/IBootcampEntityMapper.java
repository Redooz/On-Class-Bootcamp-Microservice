package com.redoz.onclass.adapters.driven.jpa.mysql.mapper;

import com.redoz.onclass.adapters.driven.jpa.mysql.entity.BootcampEntity;
import com.redoz.onclass.domain.model.Bootcamp;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IBootcampEntityMapper {
    BootcampEntity toEntity(Bootcamp bootcamp);
}
