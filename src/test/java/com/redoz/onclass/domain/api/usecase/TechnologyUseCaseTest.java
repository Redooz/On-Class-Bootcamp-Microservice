package com.redoz.onclass.domain.api.usecase;

import com.redoz.onclass.domain.exception.NoDataFoundException;
import com.redoz.onclass.domain.exception.TechnologyAlreadyExistsException;
import com.redoz.onclass.domain.model.Technology;
import com.redoz.onclass.domain.spi.ITechnologyPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TechnologyUseCaseTest {

    @Mock
    private ITechnologyPersistencePort technologyPersistencePort;

    private TechnologyUseCase technologyUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        technologyUseCase = new TechnologyUseCase(technologyPersistencePort);
    }

    @Test
    void shouldSaveTechnologyWhenNotExists() {
        Technology technology = new Technology(1L, "Java", "A programming language");
        when(technologyPersistencePort.findTechnologyByName(technology.getName())).thenReturn(Optional.empty());

        technologyUseCase.saveTechnology(technology);

        verify(technologyPersistencePort, times(1)).saveTechnology(technology);
    }

    @Test
    void shouldThrowExceptionWhenTechnologyExists() {
        Technology technology = new Technology(1L, "Java", "A programming language");
        when(technologyPersistencePort.findTechnologyByName(technology.getName())).thenReturn(Optional.of(technology));

        assertThrows(TechnologyAlreadyExistsException.class, () -> technologyUseCase.saveTechnology(technology));
    }

    @Test
    void shouldReturnAllTechnologiesWhenExists() {
        // Given
        int page = 0;
        int size = 10;
        boolean isAsc = true;
        List<Technology> expectedTechnologies = List.of(
                new Technology(1L, "Java", "A programming language"),
                new Technology(2L, "Python", "A programming language")
        );
        when(technologyPersistencePort.findAllTechnologies(page, size, isAsc)).thenReturn(expectedTechnologies);

        // When
        List<Technology> actualTechnologies = technologyUseCase.findAllTechnologies(page, size, isAsc);

        // Then
        assertEquals(expectedTechnologies, actualTechnologies);
    }

    @Test
    void shouldReturnTechnologiesInAscendingOrderWhenIsAscIsTrue() {
        // Given
        int page = 0;
        int size = 10;
        boolean isAsc = true;
        List<Technology> expectedTechnologies = List.of(
                new Technology(1L, "Java", "A programming language"),
                new Technology(2L, "Python", "A programming language")
        );
        when(technologyPersistencePort.findAllTechnologies(page, size, isAsc)).thenReturn(expectedTechnologies);

        // When
        List<Technology> actualTechnologies = technologyUseCase.findAllTechnologies(page, size, isAsc);

        // Then
        assertEquals(expectedTechnologies, actualTechnologies);
    }

    @Test
    void shouldReturnTechnologiesInDescendingOrderWhenIsAscIsFalse() {
        // Given
        int page = 0;
        int size = 10;
        boolean isAsc = false;
        List<Technology> expectedTechnologies = List.of(
                new Technology(2L, "Python", "A programming language"),
                new Technology(1L, "Java", "A programming language")
        );
        when(technologyPersistencePort.findAllTechnologies(page, size, isAsc)).thenReturn(expectedTechnologies);

        // When
        List<Technology> actualTechnologies = technologyUseCase.findAllTechnologies(page, size, isAsc);

        // Then
        assertEquals(expectedTechnologies, actualTechnologies);
    }

    @Test
    void shouldThrowNoDataFoundExceptionWhenNoTechnologiesExist() {
        int page = 0;
        int size = 10;
        boolean isAsc = true;
        when(technologyPersistencePort.findAllTechnologies(page, size, isAsc)).thenReturn(Collections.emptyList());

        assertThrows(NoDataFoundException.class, () -> technologyUseCase.findAllTechnologies(page, size, isAsc));
    }

    @Test
    void shouldReturnTechnologyWhenExists() {
        String name = "Java";
        Technology expectedTechnology = new Technology(1L, name, "A programming language");
        when(technologyPersistencePort.findTechnologyByName(name)).thenReturn(Optional.of(expectedTechnology));

        Technology actualTechnology = technologyUseCase.findTechnologyByName(name);

        assertEquals(expectedTechnology, actualTechnology);
    }

    @Test
    void shouldThrowNoDataFoundExceptionWhenTechnologyDoesNotExist() {
        String name = "NonExistentTechnology";
        when(technologyPersistencePort.findTechnologyByName(name)).thenReturn(Optional.empty());

        assertThrows(NoDataFoundException.class, () -> technologyUseCase.findTechnologyByName(name));
    }
}

