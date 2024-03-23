package com.redoz.onclass.adapters.driven.jpa.mysql.mapper;

import com.redoz.onclass.adapters.driven.jpa.mysql.entity.BootcampEntity;
import com.redoz.onclass.domain.model.Bootcamp;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IBootcampEntityMapper {
    BootcampEntity toEntity(Bootcamp bootcamp);

    List<Bootcamp> toModelList(List<BootcampEntity> bootcampEntities);
}
