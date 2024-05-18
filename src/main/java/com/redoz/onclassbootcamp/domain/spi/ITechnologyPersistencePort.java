package com.redoz.onclassbootcamp.domain.spi;

import com.redoz.onclassbootcamp.domain.model.Technology;

import java.util.List;
import java.util.Optional;

public interface ITechnologyPersistencePort {
    void saveTechnology(Technology technology);

    Optional<Technology> findTechnologyByName(String name);

    List<Technology> findAllTechnologies(int page, int size, boolean isAsc);

    Long findAllTechnologiesCount();
}
