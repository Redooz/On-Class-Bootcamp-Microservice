package com.redoz.onclass.domain.spi;

import com.redoz.onclass.domain.model.Capacity;
import com.redoz.onclass.domain.util.OrderByOption;

import java.util.List;
import java.util.Optional;

public interface ICapacityPersistencePort {
    void saveCapacity(Capacity capacity);

    List<Capacity> findAllCapacities(int page, int size, OrderByOption orderBy, boolean isAsc);

    Optional<Capacity> findCapacityByName(String name);
}
