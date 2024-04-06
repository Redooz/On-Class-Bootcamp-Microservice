package com.redoz.onclass.domain.api.usecase;

import com.redoz.onclass.domain.exception.InvalidDateException;
import com.redoz.onclass.domain.exception.NoDataFoundException;
import com.redoz.onclass.domain.model.Bootcamp;
import com.redoz.onclass.domain.model.Version;
import com.redoz.onclass.domain.spi.IVersionPersistencePort;
import com.redoz.onclass.domain.api.IBootcampServicePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class VersionUseCaseTest {

    @Mock
    private IVersionPersistencePort versionPersistencePort;

    @Mock
    private IBootcampServicePort bootcampServicePort;

    private VersionUseCase versionUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        versionUseCase = new VersionUseCase(versionPersistencePort, bootcampServicePort);
    }

    @Test
    void shouldSaveVersionWhenDatesAreValidAndBootcampExists() {
        LocalDate startDate = LocalDate.of(2021, 2, 1);
        LocalDate endDate = LocalDate.of(2021, 4, 2);
        Bootcamp bootcamp = new Bootcamp(1L, "Java", "Java bootcamp", Collections.emptyList());
        Version version = new Version(1L, bootcamp, startDate, endDate, 1);

        versionUseCase.saveVersion(version);

        verify(versionPersistencePort, times(1)).saveVersion(version);
    }

    @Test
    void shouldThrowInvalidDateExceptionWhenEndDateIsBeforeStartDate() {
        LocalDate startDate = LocalDate.of(2021, 4, 2);
        LocalDate endDate = LocalDate.of(2021, 2, 1);
        Bootcamp bootcamp = new Bootcamp(1L, "Java", "Java bootcamp", Collections.emptyList());
        Version version = new Version(1L, bootcamp, startDate, endDate, 1);

        assertThrows(InvalidDateException.class, () -> versionUseCase.saveVersion(version));
    }

    @Test
    void shouldThrowNoDataFoundExceptionWhenBootcampDoesNotExist() {
        LocalDate startDate = LocalDate.of(2021, 2, 1);
        LocalDate endDate = LocalDate.of(2021, 4, 2);
        Bootcamp bootcamp = new Bootcamp(999L, "Java", "Java bootcamp", Collections.emptyList());
        Version version = new Version(1L, bootcamp, startDate, endDate, 1);

        when(bootcampServicePort.findBootcampById(bootcamp.getId())).thenThrow(NoDataFoundException.class);

        assertThrows(NoDataFoundException.class, () -> versionUseCase.saveVersion(version));
    }
}
