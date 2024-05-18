package com.redoz.onclassbootcamp.domain.spi;

import com.redoz.onclassbootcamp.domain.model.Capacity;
import com.redoz.onclassbootcamp.domain.util.CapacityOrderByOption;

import java.util.List;
import java.util.Optional;

public interface ICapacityPersistencePort {
    void saveCapacity(Capacity capacity);

    List<Capacity> findAllCapacities(int page, int size, CapacityOrderByOption orderBy, boolean isAsc);

    Optional<Capacity> findCapacityByName(String name);

    Long findAllCapacitiesCount();
}
