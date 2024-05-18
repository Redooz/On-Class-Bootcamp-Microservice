package com.redoz.onclassbootcamp.domain.api;

import com.redoz.onclassbootcamp.domain.model.Capacity;
import com.redoz.onclassbootcamp.domain.util.CapacityOrderByOption;

import java.util.List;

public interface ICapacityServicePort {
    void saveCapacity(Capacity capacity);
    List<Capacity> findAllCapacities(int page, int size, CapacityOrderByOption orderBy, boolean isAsc);

    Capacity findCapacityByName(String name);

    Long findAllCapacitiesCount();
}
