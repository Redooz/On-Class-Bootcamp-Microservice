package com.redoz.onclass.domain.api.usecase;

import com.redoz.onclass.domain.api.ICapacityServicePort;
import com.redoz.onclass.domain.api.ITechnologyServicePort;
import com.redoz.onclass.domain.exception.DuplicateTechnologiesException;
import com.redoz.onclass.domain.exception.ExcessiveTechnologiesException;
import com.redoz.onclass.domain.exception.InsufficientTechnologiesException;
import com.redoz.onclass.domain.exception.NoDataFoundException;
import com.redoz.onclass.domain.model.Capacity;
import com.redoz.onclass.domain.model.Technology;
import com.redoz.onclass.domain.spi.ICapacityPersistencePort;
import com.redoz.onclass.domain.util.CapacityConstants;
import com.redoz.onclass.domain.util.DomainConstants;
import com.redoz.onclass.domain.util.OrderByOption;

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

        checkIfTechnologiesExists(capacity.getTechnologies());

        capacityPersistencePort.saveCapacity(capacity);
    }

    @Override
    public List<Capacity> findAllCapacities(int page, int size, OrderByOption orderBy, boolean isAsc) {
        List<Capacity> capacities = capacityPersistencePort.findAllCapacities(page, size, orderBy, isAsc);

        if (capacities.isEmpty()) {
            throw new NoDataFoundException(DomainConstants.NO_DATA_FOUND_CAPACITY_EXCEPTION_MESSAGE);
        }

        return capacities;
    }

    private boolean technologiesAreUnique(List<Technology> technologies) {
        Set<String> uniqueTechnologies = Set.copyOf(technologies.stream().map(Technology::getName).toList());
        return uniqueTechnologies.size() == technologies.size();
    }

    private void checkIfTechnologiesExists(List<Technology> technologies) {
        for (Technology technology : technologies) {
            // findTechnologyByName throws NoDataFoundException if the technology does not exist
            technologyServicePort.findTechnologyByName(technology.getName());
        }

    }
}
