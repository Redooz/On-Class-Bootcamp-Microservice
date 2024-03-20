package com.redoz.onclass.domain.api;

import com.redoz.onclass.domain.model.Technology;

import java.util.List;

public interface ITechnologyServicePort {
    void saveTechnology(Technology technology);

    List<Technology> findAllTechnologies(int page, int size, boolean isAsc);

    Technology findTechnologyByName(String name);
}
