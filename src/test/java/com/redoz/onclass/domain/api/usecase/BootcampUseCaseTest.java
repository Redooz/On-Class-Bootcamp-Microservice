package com.redoz.onclass.domain.api.usecase;

import static org.junit.jupiter.api.Assertions.*;

import com.redoz.onclass.domain.exception.DuplicateCapacitiesException;
import com.redoz.onclass.domain.exception.ExcessiveCapacitiesException;
import com.redoz.onclass.domain.exception.InsufficientCapacitiesException;
import com.redoz.onclass.domain.model.Bootcamp;
import com.redoz.onclass.domain.model.Capacity;
import com.redoz.onclass.domain.spi.IBootcampPersistencePort;
import com.redoz.onclass.domain.api.ICapacityServicePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;

class BootcampUseCaseTest {

    @Mock
    private IBootcampPersistencePort bootcampPersistencePort;

    @Mock
    private ICapacityServicePort capacityServicePort;

    private BootcampUseCase bootcampUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        bootcampUseCase = new BootcampUseCase(bootcampPersistencePort, capacityServicePort);
    }

    @Test
    void shouldSaveBootcampWhenCapacitiesAreWithinLimitAndUnique() {
        Capacity capacity1 = new Capacity(1L,"Capacity1", "", Collections.emptyList());
        Capacity capacity2 = new Capacity(2L, "Capacity2", "", Collections.emptyList());
        List<Capacity> capacities = List.of(capacity1, capacity2);
        Bootcamp bootcamp = new Bootcamp(1L, "Bootcamp1", "Desc1", capacities);

        bootcampUseCase.saveBootcamp(bootcamp);

        verify(bootcampPersistencePort, times(1)).saveBootcamp(bootcamp);
    }

    @Test
    void shouldThrowInsufficientCapacitiesExceptionWhenCapacitiesAreBelowLimit() {
        List<Capacity> capacities = Collections.emptyList();
        Bootcamp bootcamp = new Bootcamp(1L, "Bootcamp1", "Desc1", capacities);

        assertThrows(InsufficientCapacitiesException.class, () -> bootcampUseCase.saveBootcamp(bootcamp));
    }

    @Test
    void shouldThrowExcessiveCapacitiesExceptionWhenCapacitiesAreAboveLimit() {
        Capacity capacity1 = new Capacity(1L,"Capacity1", "", Collections.emptyList());
        Capacity capacity2 = new Capacity(2L, "Capacity2", "", Collections.emptyList());
        Capacity capacity3 = new Capacity(3L, "Capacity3", "", Collections.emptyList());
        Capacity capacity4 = new Capacity(4L, "Capacity4", "", Collections.emptyList());
        Capacity capacity5 = new Capacity(5L, "Capacity5", "", Collections.emptyList());

        List<Capacity> capacities = List.of(capacity1, capacity2, capacity3, capacity4, capacity5);
        Bootcamp bootcamp = new Bootcamp(1L, "Bootcamp1", "Desc1", capacities);

        assertThrows(ExcessiveCapacitiesException.class, () -> bootcampUseCase.saveBootcamp(bootcamp));
    }

    @Test
    void shouldThrowDuplicateCapacitiesExceptionWhenCapacitiesAreNotUnique() {
        Capacity capacity1 = new Capacity(1L,"Capacity", "", Collections.emptyList());
        Capacity capacity2 = new Capacity(1L, "Capacity", "", Collections.emptyList());
        Capacity capacity3 = new Capacity(3L, "Capacity3", "", Collections.emptyList());

        List<Capacity> capacities = Arrays.asList(capacity1, capacity2, capacity3);
        Bootcamp bootcamp = new Bootcamp(1L, "Bootcamp1", "Desc1", capacities);

        assertThrows(DuplicateCapacitiesException.class, () -> bootcampUseCase.saveBootcamp(bootcamp));
    }
}
