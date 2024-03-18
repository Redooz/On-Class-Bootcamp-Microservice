package com.redoz.onclass.domain.spi;

import com.redoz.onclass.domain.model.Capacity;

public interface ICapacityPersistencePort {
    void saveCapacity(Capacity capacity);
}
