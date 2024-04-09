package com.redoz.onclassbootcamp.domain.api.usecase;

import com.redoz.onclassbootcamp.domain.exception.InvalidDateException;
import com.redoz.onclassbootcamp.domain.exception.NoDataFoundException;
import com.redoz.onclassbootcamp.domain.model.Bootcamp;
import com.redoz.onclassbootcamp.domain.model.Version;
import com.redoz.onclassbootcamp.domain.spi.IVersionPersistencePort;
import com.redoz.onclassbootcamp.domain.api.IBootcampServicePort;
import com.redoz.onclassbootcamp.domain.util.VersionOrderByOption;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

    @Test
    void shouldReturnVersionsWhenVersionsExist() {
        LocalDate startDate = LocalDate.of(2021, 2, 1);
        LocalDate endDate = LocalDate.of(2021, 4, 2);
        List<Version> expectedVersions = getVersions(startDate, endDate);
        when(versionPersistencePort.findAllVersions(anyInt(), anyInt(), any(), anyBoolean(), anyLong())).thenReturn(expectedVersions);

        List<Version> actualVersions = versionUseCase.findAllVersions(0, 10, VersionOrderByOption.START_DATE, true, 0);

        assertEquals(expectedVersions, actualVersions);
    }

    @Test
    void shouldReturnVersionsWhenVersionsExistAndBootcampIdIsProvided() {
        long bootcampId = 1L;
        LocalDate startDate = LocalDate.of(2021, 2, 1);
        LocalDate endDate = LocalDate.of(2021, 4, 2);
        Bootcamp bootcamp1 = new Bootcamp(bootcampId, "Java", "Java bootcamp", Collections.emptyList());
        Version version1 = new Version(1L, bootcamp1, startDate, endDate, 1);

        List<Version> expectedVersions = List.of(version1);
        when(versionPersistencePort.findAllVersions(anyInt(), anyInt(), any(), anyBoolean(), eq(bootcampId))).thenReturn(expectedVersions);

        List<Version> actualVersions = versionUseCase.findAllVersions(0, 10, VersionOrderByOption.START_DATE, true, bootcampId);

        assertEquals(expectedVersions, actualVersions);
    }

    @Test
    void shouldThrowNoDataFoundExceptionWhenNoVersionsExist() {
        when(versionPersistencePort.findAllVersions(anyInt(), anyInt(), any(), anyBoolean(), anyLong())).thenReturn(Collections.emptyList());

        assertThrows(NoDataFoundException.class, () -> versionUseCase.findAllVersions(0, 10, VersionOrderByOption.START_DATE, true, 1L));
    }

    private static List<Version> getVersions(LocalDate startDate, LocalDate endDate) {
        Bootcamp bootcamp1 = new Bootcamp(1L, "Java", "Java bootcamp", Collections.emptyList());
        Version version1 = new Version(1L, bootcamp1, startDate, endDate, 1);
        Bootcamp bootcamp2 = new Bootcamp(2L, "Python", "Python bootcamp", Collections.emptyList());
        Version version2 = new Version(2L, bootcamp2, startDate, endDate, 1);
        Bootcamp bootcamp3 = new Bootcamp(3L, "JavaScript", "JavaScript bootcamp", Collections.emptyList());
        Version version3 = new Version(3L, bootcamp3, startDate, endDate, 1);

        return List.of(version1, version2, version3);
    }
}
