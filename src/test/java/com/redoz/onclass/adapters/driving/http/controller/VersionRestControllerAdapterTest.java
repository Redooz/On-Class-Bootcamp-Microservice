package com.redoz.onclass.adapters.driving.http.controller;

import com.redoz.onclass.adapters.driving.http.dto.request.CreateVersionBootcampItem;
import com.redoz.onclass.adapters.driving.http.dto.request.CreateVersionRequest;
import com.redoz.onclass.adapters.driving.http.mapper.IVersionRequestMapper;
import com.redoz.onclass.domain.api.IVersionServicePort;
import com.redoz.onclass.domain.model.Bootcamp;
import com.redoz.onclass.domain.model.Version;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Collections;

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

}
