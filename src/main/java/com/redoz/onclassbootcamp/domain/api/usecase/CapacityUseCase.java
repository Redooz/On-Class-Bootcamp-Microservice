package com.redoz.onclassbootcamp.domain.api.usecase;

import com.redoz.onclassbootcamp.domain.api.ICapacityServicePort;
import com.redoz.onclassbootcamp.domain.api.ITechnologyServicePort;
import com.redoz.onclassbootcamp.domain.exception.*;
import com.redoz.onclassbootcamp.domain.model.Capacity;
import com.redoz.onclassbootcamp.domain.model.Technology;
import com.redoz.onclassbootcamp.domain.spi.ICapacityPersistencePort;
import com.redoz.onclassbootcamp.domain.util.CapacityConstants;
import com.redoz.onclassbootcamp.domain.util.DomainConstants;
import com.redoz.onclassbootcamp.domain.util.CapacityOrderByOption;

import java.util.List;
import java.util.Set;

public class CapacityUseCase implements ICapacityServicePort {
    private final ICapacityPersistencePort capacityPersistencePort;
    private final ITechnologyServicePort technologyServicePort;

    public CapacityUseCase(ICapacityPersistencePort capacityPersistencePort, ITechnologyServicePort technologyServicePort) {
        this.capacityPersistencePort = capacityPersistencePort;
        this.technologyServicePort = technologyServicePort;
    }

    @Override
    public void saveCapacity(Capacity capacity) {
        if (capacity.getTechnologies().size() < CapacityConstants.MIN_TECHNOLOGIES) {
            throw new InsufficientTechnologiesException(capacity.getName());
        }

        if (capacity.getTechnologies().size() > CapacityConstants.MAX_TECHNOLOGIES) {
            throw new ExcessiveTechnologiesException(capacity.getName());
        }

        if (!technologiesAreUnique(capacity.getTechnologies())) {
            throw new DuplicateTechnologiesException(capacity.getName());
        }

        if (capacityPersistencePort.findCapacityByName(capacity.getName()).isPresent()) {
            throw new CapacityAlreadyExistsException(capacity.getName());
        }

        checkIfTechnologiesExists(capacity.getTechnologies());

        capacityPersistencePort.saveCapacity(capacity);
    }

    @Override
    public List<Capacity> findAllCapacities(int page, int size, CapacityOrderByOption orderBy, boolean isAsc) {
        List<Capacity> capacities = capacityPersistencePort.findAllCapacities(page, size, orderBy, isAsc);

        if (capacities.isEmpty()) {
            throw new NoDataFoundException(DomainConstants.NO_DATA_FOUND_CAPACITY_EXCEPTION_MESSAGE);
        }

        return capacities;
    }

    @Override
    public Capacity findCapacityByName(String name) {
        return capacityPersistencePort.findCapacityByName(name).orElseThrow(() -> new NoDataFoundException(name));
    }

    @Override
    public Long findAllCapacitiesCount() {
        return capacityPersistencePort.findAllCapacitiesCount();
    }

    private boolean technologiesAreUnique(List<Technology> technologies) {
        Set<String> uniqueTechnologiesNames = Set.copyOf(technologies.stream().map(Technology::getName).toList());
        Set<Long> uniqueTechnologiesIds = Set.copyOf(technologies.stream().map(Technology::getId).toList());

        return uniqueTechnologiesNames.size() == technologies.size() && uniqueTechnologiesIds.size() == technologies.size();
    }

    private void checkIfTechnologiesExists(List<Technology> technologies) {
        for (Technology technology : technologies) {
            // findTechnologyByName throws NoDataFoundException if the technology does not exist
            technologyServicePort.findTechnologyByName(technology.getName());
        }

    }
}
