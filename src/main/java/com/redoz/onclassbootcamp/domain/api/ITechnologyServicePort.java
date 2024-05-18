package com.redoz.onclassbootcamp.domain.api;

import com.redoz.onclassbootcamp.domain.model.Technology;

import java.util.List;

public interface ITechnologyServicePort {
    void saveTechnology(Technology technology);

    List<Technology> findAllTechnologies(int page, int size, boolean isAsc);

    Technology findTechnologyByName(String name);

    Long findAllTechnologiesCount();
}
