package com.redoz.onclass.domain.api.usecase;

import com.redoz.onclass.domain.api.ITechnologyServicePort;
import com.redoz.onclass.domain.exception.DuplicateTechnologiesException;
import com.redoz.onclass.domain.exception.ExcessiveTechnologiesException;
import com.redoz.onclass.domain.exception.InsufficientTechnologiesException;
import com.redoz.onclass.domain.exception.NoDataFoundException;
import com.redoz.onclass.domain.model.Capacity;
import com.redoz.onclass.domain.model.Technology;
import com.redoz.onclass.domain.spi.ICapacityPersistencePort;
import com.redoz.onclass.domain.util.CapacityConstants;
import com.redoz.onclass.domain.util.CapacityOrderByOption;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class CapacityUseCaseTest {

    @Mock
    private ICapacityPersistencePort capacityPersistencePort;
    @Mock
    private ITechnologyServicePort technologyServicePort;

    private CapacityUseCase capacityUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        capacityUseCase = new CapacityUseCase(capacityPersistencePort, technologyServicePort);
    }

    @Test
    void shouldSaveCapacityWhenTechnologiesAreValid() {
        List<Technology> technologies = List.of(
                new Technology(1L, "Java", "A programming language"),
                new Technology(2L, "Spring", "A framework"),
                new Technology(2L, "Mockito", "A testing library")
        );
        Capacity capacity = new Capacity(1L, "Capacity1", "Desc1", technologies);

        capacityUseCase.saveCapacity(capacity);

        verify(capacityPersistencePort, times(1)).saveCapacity(capacity);
    }

    @Test
    void shouldThrowInsufficientTechnologiesExceptionWhenTechnologiesAreLessThanMin() {
        Capacity capacity = new Capacity(1L, "Capacity1", "Desc1" ,Collections.emptyList());

        assertThrows(InsufficientTechnologiesException.class, () -> capacityUseCase.saveCapacity(capacity));

        verify(capacityPersistencePort, times(0)).saveCapacity(any());
    }

    @Test
    void shouldThrowExcessiveTechnologiesExceptionWhenTechnologiesAreMoreThanMax() {
        List<Technology> technologies = new ArrayList<>();
        for (int i = 0; i <= CapacityConstants.MAX_TECHNOLOGIES; i++) {
            technologies.add(new Technology((long) i, "Tech" + i, "Description" + i));
        }
        Capacity capacity = new Capacity(1L, "Capacity1", "Desc1", technologies);

        assertThrows(ExcessiveTechnologiesException.class, () -> capacityUseCase.saveCapacity(capacity));

        verify(capacityPersistencePort, times(0)).saveCapacity(any());
    }

    @Test
    void shouldThrowDuplicateTechnologiesExceptionWhenTechnologiesAreNotUnique() {
        List<Technology> technologies = List.of(
                new Technology(1L, "Tech1", "A programming language"),
                new Technology(1L, "Name1", "A programming language"),
                new Technology(2L, "Name1", "A programming language")
        );
        Capacity capacity = new Capacity(1L, "Capacity1", "Desc1", technologies);

        assertThrows(DuplicateTechnologiesException.class, () -> capacityUseCase.saveCapacity(capacity));

        verify(capacityPersistencePort, times(0)).saveCapacity(any());
    }

    @Test
    void shouldReturnCapacitiesWhenCapacitiesAreFound() {
        // Given
        int page = 0;
        int size = 10;
        CapacityOrderByOption orderBy = CapacityOrderByOption.NAME;
        boolean isAsc = true;
        List<Capacity> capacities = List.of(new Capacity(1L, "Capacity1", "Desc1", List.of()));
        when(capacityPersistencePort.findAllCapacities(page, size, orderBy, isAsc)).thenReturn(capacities);

        // When
        List<Capacity> result = capacityUseCase.findAllCapacities(page, size, orderBy, isAsc);

        // Then
        assertEquals(capacities, result);
        verify(capacityPersistencePort, times(1)).findAllCapacities(page, size, orderBy, isAsc);
    }

    @Test
    void shouldThrowNoDataFoundExceptionWhenNoCapacitiesAreFound() {
        // Given
        int page = 0;
        int size = 10;
        CapacityOrderByOption orderBy = CapacityOrderByOption.NAME;
        boolean isAsc = true;
        when(capacityPersistencePort.findAllCapacities(page, size, orderBy, isAsc)).thenReturn(Collections.emptyList());

        // When & Then
        assertThrows(NoDataFoundException.class, () -> capacityUseCase.findAllCapacities(page, size, orderBy, isAsc));
        verify(capacityPersistencePort, times(1)).findAllCapacities(page, size, orderBy, isAsc);
    }

}
