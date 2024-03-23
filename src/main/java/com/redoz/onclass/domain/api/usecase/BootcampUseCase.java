package com.redoz.onclass.domain.api.usecase;

import com.redoz.onclass.domain.api.IBootcampServicePort;
import com.redoz.onclass.domain.api.ICapacityServicePort;
import com.redoz.onclass.domain.exception.DuplicateCapacitiesException;
import com.redoz.onclass.domain.exception.ExcessiveCapacitiesException;
import com.redoz.onclass.domain.exception.InsufficientCapacitiesException;
import com.redoz.onclass.domain.model.Bootcamp;
import com.redoz.onclass.domain.model.Capacity;
import com.redoz.onclass.domain.spi.IBootcampPersistencePort;
import com.redoz.onclass.domain.util.BootcampConstants;

import java.util.List;
import java.util.Set;

public class BootcampUseCase implements IBootcampServicePort {
    private final IBootcampPersistencePort bootcampPersistencePort;
    private final ICapacityServicePort capacityServicePort;

    public BootcampUseCase(IBootcampPersistencePort bootcampPersistencePort, ICapacityServicePort capacityServicePort) {
        this.bootcampPersistencePort = bootcampPersistencePort;
        this.capacityServicePort = capacityServicePort;
    }

    @Override
    public void saveBootcamp(Bootcamp bootcamp) {
        if (bootcamp.getCapacities().size() < BootcampConstants.MIN_CAPACITIES) {
            throw new InsufficientCapacitiesException(bootcamp.getName());
        }

        if (bootcamp.getCapacities().size() > BootcampConstants.MAX_CAPACITIES) {
            throw new ExcessiveCapacitiesException(bootcamp.getName());
        }

        if (!capacitiesAreUnique(bootcamp.getCapacities())) {
            throw new DuplicateCapacitiesException(bootcamp.getName());
        }

        checkIfCapacitiesExist(bootcamp.getCapacities());

        bootcampPersistencePort.saveBootcamp(bootcamp);
    }

    private void checkIfCapacitiesExist(List<Capacity> capacities) {
        for (Capacity capacity : capacities) {
            // findCapacityByName throws NoDataFoundException if no capacity is found
            capacityServicePort.findCapacityByName(capacity.getName());
        }
    }

    private boolean capacitiesAreUnique(List<Capacity> capacities) {
        Set<String> uniqueCapacities = Set.copyOf(capacities.stream().map(Capacity::getName).toList());
        return uniqueCapacities.size() == capacities.size();
    }
}
