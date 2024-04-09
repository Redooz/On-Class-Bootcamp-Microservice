package com.redoz.onclassbootcamp.domain.api.usecase;

import com.redoz.onclassbootcamp.domain.api.ITechnologyServicePort;
import com.redoz.onclassbootcamp.domain.exception.NoDataFoundException;
import com.redoz.onclassbootcamp.domain.exception.TechnologyAlreadyExistsException;
import com.redoz.onclassbootcamp.domain.model.Technology;
import com.redoz.onclassbootcamp.domain.spi.ITechnologyPersistencePort;
import com.redoz.onclassbootcamp.domain.util.DomainConstants;

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
    public Technology findTechnologyByName(String name) {
        return technologyPersistencePort.findTechnologyByName(name)
                .orElseThrow(() -> new NoDataFoundException(name));
    }

    @Override
    public List<Technology> findAllTechnologies(int page, int size, boolean isAsc) {
        List<Technology> technologies = technologyPersistencePort.findAllTechnologies(page, size, isAsc);

        if (technologies.isEmpty()){
            throw new NoDataFoundException(DomainConstants.NO_DATA_FOUND_TECHNOLOGY_EXCEPTION_MESSAGE);
        }

        return technologies;
    }
}
