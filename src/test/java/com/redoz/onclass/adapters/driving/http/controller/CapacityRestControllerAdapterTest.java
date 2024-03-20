package com.redoz.onclass.adapters.driving.http.controller;

import com.redoz.onclass.adapters.driving.http.dto.request.CapacityTechnologyItem;
import com.redoz.onclass.adapters.driving.http.dto.request.CreateCapacityRequest;
import com.redoz.onclass.adapters.driving.http.mapper.ICapacityRequestMapper;
import com.redoz.onclass.domain.api.ICapacityServicePort;
import com.redoz.onclass.domain.model.Capacity;
import com.redoz.onclass.domain.model.Technology;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CapacityRestControllerAdapterTest {

    @Mock
    private ICapacityServicePort capacityServicePort;

    @Mock
    private ICapacityRequestMapper capacityRequestMapper;

    private CapacityRestControllerAdapter capacityRestControllerAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        capacityRestControllerAdapter = new CapacityRestControllerAdapter(capacityServicePort, capacityRequestMapper);
    }

    @Test
    void shouldReturnCreatedWhenCapacityIsCreated() {
        List<CapacityTechnologyItem> technologiesItems = List.of(
                new CapacityTechnologyItem(1L, "Java"),
                new CapacityTechnologyItem(2L, "Spring"));
        CreateCapacityRequest createCapacityRequest = new CreateCapacityRequest("Capacity1", "Desc1", technologiesItems);

        List<Technology> technologies = List.of(
                new Technology(1L, "Java", "A programming language", new ArrayList<>()),
                new Technology(2L, "Spring", "A framework", new ArrayList<>()));
        when(capacityRequestMapper.toModel(createCapacityRequest)).thenReturn(new Capacity(1L, "Capacity1", "Desc1", technologies));
        Capacity capacity = new Capacity(1L, "Capacity1", "Desc1", technologies);

        when(capacityRequestMapper.toModel(createCapacityRequest)).thenReturn(capacity);

        ResponseEntity<Void> responseEntity = capacityRestControllerAdapter.createCapacity(createCapacityRequest);

        verify(capacityServicePort, times(1)).saveCapacity(capacity);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

}
