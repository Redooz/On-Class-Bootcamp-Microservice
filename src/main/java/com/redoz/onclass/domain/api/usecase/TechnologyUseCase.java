package com.redoz.onclass.domain.api.usecase;

import com.redoz.onclass.domain.api.ITechnologyServicePort;
import com.redoz.onclass.domain.exception.TechnologyAlreadyExistsException;
import com.redoz.onclass.domain.model.Technology;
import com.redoz.onclass.domain.spi.ITechnologyPersistencePort;

import java.util.List;

public class TechnologyUseCase implements ITechnologyServicePort {
    private final ITechnologyPersistencePort technologyPersistencePort;

    public TechnologyUseCase(ITechnologyPersistencePort technologyPersistencePort) {
        this.technologyPersistencePort = technologyPersistencePort;
    }

    @Override
    public void saveTechnology(Technology technology) {
        if (technologyPersistencePort.findTechnologyByName(technology.getName()).isPresent()){
            throw new TechnologyAlreadyExistsException(technology.getName());
        }

        technologyPersistencePort.saveTechnology(technology);
    }

    @Override
    public List<Technology> findAllTechnologies(int page, int size, boolean isAsc) {
        return technologyPersistencePort.findAllTechnologies(page, size, isAsc);
    }
}
