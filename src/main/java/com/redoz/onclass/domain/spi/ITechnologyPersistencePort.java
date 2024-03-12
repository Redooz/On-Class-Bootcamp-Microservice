package com.redoz.onclass.domain.spi;

import com.redoz.onclass.domain.model.Technology;

import java.util.List;

public interface ITechnologyPersistencePort {
    void saveTechnology(Technology technology);
}
