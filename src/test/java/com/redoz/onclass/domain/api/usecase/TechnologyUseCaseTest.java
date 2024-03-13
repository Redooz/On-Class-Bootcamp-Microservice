package com.redoz.onclass.domain.api.usecase;

import com.redoz.onclass.domain.exception.TechnologyAlreadyExistsException;
import com.redoz.onclass.domain.model.Technology;
import com.redoz.onclass.domain.spi.ITechnologyPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
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
        Technology technology = new Technology(1L, "Java", "A programming language", "Software Development");
        when(technologyPersistencePort.findTechnologyByName(technology.getName())).thenReturn(Optional.empty());

        technologyUseCase.saveTechnology(technology);

        verify(technologyPersistencePort, times(1)).saveTechnology(technology);
    }

    @Test
    void shouldThrowExceptionWhenTechnologyExists() {
        Technology technology = new Technology(1L, "Java", "A programming language", "Software Development");
        when(technologyPersistencePort.findTechnologyByName(technology.getName())).thenReturn(Optional.of(technology));

        assertThrows(TechnologyAlreadyExistsException.class, () -> technologyUseCase.saveTechnology(technology));
    }
}

