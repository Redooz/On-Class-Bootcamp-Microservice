package com.redoz.onclassbootcamp.domain.api.usecase;

import com.redoz.onclassbootcamp.domain.api.IBootcampServicePort;
import com.redoz.onclassbootcamp.domain.api.ICapacityServicePort;
import com.redoz.onclassbootcamp.domain.exception.DuplicateCapacitiesException;
import com.redoz.onclassbootcamp.domain.exception.ExcessiveCapacitiesException;
import com.redoz.onclassbootcamp.domain.exception.InsufficientCapacitiesException;
import com.redoz.onclassbootcamp.domain.exception.NoDataFoundException;
import com.redoz.onclassbootcamp.domain.model.Bootcamp;
import com.redoz.onclassbootcamp.domain.model.Capacity;
import com.redoz.onclassbootcamp.domain.spi.IBootcampPersistencePort;
import com.redoz.onclassbootcamp.domain.util.BootcampConstants;
import com.redoz.onclassbootcamp.domain.util.BootcampOrderByOption;
import com.redoz.onclassbootcamp.domain.util.DomainConstants;

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

    @Override
    public List<Bootcamp> findAllBootcamps(int page, int size, BootcampOrderByOption orderBy, boolean isAsc) {
        List<Bootcamp> bootcamps = bootcampPersistencePort.findAllBootcamps(page, size, orderBy, isAsc);

        if (bootcamps.isEmpty()) {
            throw new NoDataFoundException(DomainConstants.NO_DATA_FOUND_BOOTCAMP_EXCEPTION_MESSAGE);
        }

        return bootcamps;
    }

    @Override
    public Bootcamp findBootcampById(Long id) {
        return bootcampPersistencePort.findBootcampById(id)
                .orElseThrow(() -> new NoDataFoundException(DomainConstants.NO_DATA_FOUND_BOOTCAMP_EXCEPTION_MESSAGE));
    }

    private void checkIfCapacitiesExist(List<Capacity> capacities) {
        for (Capacity capacity : capacities) {
            // findCapacityByName throws NoDataFoundException if no capacity is found
            capacityServicePort.findCapacityByName(capacity.getName());
        }
    }

    private boolean capacitiesAreUnique(List<Capacity> capacities) {
        Set<Long> uniqueCapacitiesId = Set.copyOf(capacities.stream().map(Capacity::getId).toList());
        Set<String> uniqueCapacitiesName = Set.copyOf(capacities.stream().map(Capacity::getName).toList());

        return uniqueCapacitiesName.size() == capacities.size() && uniqueCapacitiesId.size() == capacities.size();
    }
}