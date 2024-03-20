package com.redoz.onclass.domain.api.usecase;

import com.redoz.onclass.domain.api.ICapacityServicePort;
import com.redoz.onclass.domain.exception.DuplicateTechnologiesException;
import com.redoz.onclass.domain.exception.ExcessiveTechnologiesException;
import com.redoz.onclass.domain.exception.InsufficientTechnologiesException;
import com.redoz.onclass.domain.model.Capacity;
import com.redoz.onclass.domain.model.Technology;
import com.redoz.onclass.domain.spi.ICapacityPersistencePort;
import com.redoz.onclass.domain.util.CapacityConstants;

import java.util.List;
import java.util.Set;

public class CapacityUseCase implements ICapacityServicePort {
    private final ICapacityPersistencePort capacityPersistencePort;

    public CapacityUseCase(ICapacityPersistencePort capacityPersistencePort) {
        this.capacityPersistencePort = capacityPersistencePort;
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

        capacityPersistencePort.saveCapacity(capacity);
    }

    private boolean technologiesAreUnique(List<Technology> technologies) {
        Set<String> uniqueTechnologies = Set.copyOf(technologies.stream().map(Technology::getName).toList());
        return uniqueTechnologies.size() == technologies.size();
    }
}
