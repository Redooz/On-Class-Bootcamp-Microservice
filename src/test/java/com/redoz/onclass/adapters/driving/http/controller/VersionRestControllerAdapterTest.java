package com.redoz.onclass.adapters.driving.http.controller;

import com.redoz.onclass.adapters.driving.http.dto.request.CreateVersionBootcampItem;
import com.redoz.onclass.adapters.driving.http.dto.request.CreateVersionRequest;
import com.redoz.onclass.adapters.driving.http.dto.response.FindVersionBootcampItem;
import com.redoz.onclass.adapters.driving.http.dto.response.FindVersionResponse;
import com.redoz.onclass.adapters.driving.http.mapper.IVersionRequestMapper;
import com.redoz.onclass.domain.api.IVersionServicePort;
import com.redoz.onclass.domain.model.Bootcamp;
import com.redoz.onclass.domain.model.Version;
import com.redoz.onclass.domain.util.VersionOrderByOption;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class VersionRestControllerAdapterTest {

    @Mock
    private IVersionServicePort versionServicePort;

    @Mock
    private IVersionRequestMapper versionRequestMapper;

    @InjectMocks
    private VersionRestControllerAdapter versionRestControllerAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnCreatedWhenVersionIsCreated() {
        LocalDate startDate = LocalDate.of(2021, 2, 1);
        LocalDate endDate = LocalDate.of(2021, 4, 2);
        CreateVersionBootcampItem bootcamp = new CreateVersionBootcampItem(1L);
        CreateVersionRequest createVersionRequest = new CreateVersionRequest(startDate, endDate, 1, bootcamp);
        Bootcamp bootcampModel = new Bootcamp(1L, "Java", "Java bootcamp", Collections.emptyList());
        Version version = new Version(1L, bootcampModel, startDate, endDate, 1);
        when(versionRequestMapper.toModel(createVersionRequest)).thenReturn(version);

        ResponseEntity<Void> responseEntity = versionRestControllerAdapter.createVersion(createVersionRequest);

        verify(versionServicePort, times(1)).saveVersion(version);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

    @Test
    void shouldReturnOkWhenVersionsAreFound() {
        LocalDate startDate = LocalDate.of(2021, 2, 1);
        LocalDate endDate = LocalDate.of(2021, 4, 2);
        List<Version> versions = getVersions(startDate, endDate);
        List<FindVersionResponse> responseList = List.of(
                new FindVersionResponse(1L, startDate, endDate, 1, new FindVersionBootcampItem(1L, "Java")),
                new FindVersionResponse(2L, startDate, endDate, 1, new FindVersionBootcampItem(2L, "Python")),
                new FindVersionResponse(3L, startDate, endDate, 1, new FindVersionBootcampItem(3L, "JavaScript"))
        );
        when(versionServicePort.findAllVersions(0, 10, VersionOrderByOption.BOOTCAMP_NAME, true, 0)).thenReturn(versions);
        when(versionRequestMapper.modelToFindResponseList(versions)).thenReturn(responseList);

        ResponseEntity<List<FindVersionResponse>> responseEntity = versionRestControllerAdapter.findAllVersions(0, 10, "BOOTCAMP_NAME", true, 0);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(responseList, responseEntity.getBody());

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
