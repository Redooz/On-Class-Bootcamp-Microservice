package com.redoz.onclassbootcamp.adapters.driven.jpa.mysql.mapper;

import com.redoz.onclassbootcamp.adapters.driven.jpa.mysql.entity.BootcampEntity;
import com.redoz.onclassbootcamp.domain.model.Bootcamp;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IBootcampEntityMapper {
    BootcampEntity toEntity(Bootcamp bootcamp);

    List<Bootcamp> toModelList(List<BootcampEntity> bootcampEntities);

    Bootcamp toModel(BootcampEntity bootcampEntity);
}
