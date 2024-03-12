package com.redoz.onclass.domain.api.usecase;

import com.redoz.onclass.domain.api.ITechnologyServicePort;
import com.redoz.onclass.domain.model.Technology;
import com.redoz.onclass.domain.spi.ITechnologyPersistencePort;

public class TechnologyUseCase implements ITechnologyServicePort {
    private ITechnologyPersistencePort technologyPersistencePort;

    public TechnologyUseCase(ITechnologyPersistencePort technologyPersistencePort) {
        this.technologyPersistencePort = technologyPersistencePort;
    }


    @Override
    public void saveTechnology(Technology technology) {
        technologyPersistencePort.saveTechnology(technology);
    }
}
