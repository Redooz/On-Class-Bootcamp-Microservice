package com.redoz.onclass.domain.api;

import com.redoz.onclass.domain.model.Capacity;
import com.redoz.onclass.domain.util.OrderByOption;

import java.util.List;

public interface ICapacityServicePort {
    void saveCapacity(Capacity capacity);
    List<Capacity> findAllCapacities(int page, int size, OrderByOption orderBy, boolean isAsc);
}
