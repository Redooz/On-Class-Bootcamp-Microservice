package com.redoz.onclass.adapters.driving.http.controller;

import com.redoz.onclass.adapters.driving.http.dto.CapacityTechnologyItem;
import com.redoz.onclass.adapters.driving.http.dto.request.CreateCapacityRequest;
import com.redoz.onclass.adapters.driving.http.dto.response.FindCapacityResponse;
import com.redoz.onclass.adapters.driving.http.mapper.ICapacityRequestMapper;
import com.redoz.onclass.domain.api.ICapacityServicePort;
import com.redoz.onclass.domain.model.Capacity;
import com.redoz.onclass.domain.model.Technology;
import com.redoz.onclass.domain.util.CapacityOrderByOption;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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
                new Technology(1L, "Java", "A programming language"),
                new Technology(2L, "Spring", "A framework"));
        when(capacityRequestMapper.toModel(createCapacityRequest)).thenReturn(new Capacity(1L, "Capacity1", "Desc1", technologies));
        Capacity capacity = new Capacity(1L, "Capacity1", "Desc1", technologies);

        when(capacityRequestMapper.toModel(createCapacityRequest)).thenReturn(capacity);

        ResponseEntity<Void> responseEntity = capacityRestControllerAdapter.createCapacity(createCapacityRequest);

        verify(capacityServicePort, times(1)).saveCapacity(capacity);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

    @Test
    void shouldReturnCapacitiesInAscendingOrderWhenIsAscIsTrueAndOrderByName() {
        // Given
        int page = 0;
        int size = 10;
        boolean isAsc = true;
        List<Capacity> expectedCapacities = List.of(
                new Capacity(1L, "ACapacity", "Desc1", Collections.emptyList()),
                new Capacity(2L, "BCapacity", "Desc2", Collections.emptyList())
        );
        when(capacityServicePort.findAllCapacities(page, size, CapacityOrderByOption.NAME, isAsc)).thenReturn(expectedCapacities);
        List<FindCapacityResponse> expectedResponses = expectedCapacities.stream()
                .map(capacityRequestMapper::modelToFindResponse)
                .toList();

        // When
        ResponseEntity<List<FindCapacityResponse>> responseEntity = capacityRestControllerAdapter.findAllCapacities(page, size, "name", isAsc);

        // Then
        verify(capacityServicePort, times(1)).findAllCapacities(page, size, CapacityOrderByOption.NAME, isAsc);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedResponses, responseEntity.getBody());
    }

    @Test
    void shouldReturnCapacitiesInDescendingOrderWhenIsAscIsFalseAndOrderByName() {
        // Given
        int page = 0;
        int size = 10;
        boolean isAsc = false;
        List<Capacity> expectedCapacities = List.of(
                new Capacity(2L, "BCapacity", "Desc2", Collections.emptyList()),
                new Capacity(1L, "ACapacity", "Desc1", Collections.emptyList())
        );
        when(capacityServicePort.findAllCapacities(page, size, CapacityOrderByOption.NAME, isAsc)).thenReturn(expectedCapacities);
        List<FindCapacityResponse> expectedResponses = expectedCapacities.stream()
                .map(capacityRequestMapper::modelToFindResponse)
                .toList();

        // When
        ResponseEntity<List<FindCapacityResponse>> responseEntity = capacityRestControllerAdapter.findAllCapacities(page, size, "name", isAsc);

        // Then
        verify(capacityServicePort, times(1)).findAllCapacities(page, size, CapacityOrderByOption.NAME, isAsc);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedResponses, responseEntity.getBody());
    }

    @Test
    void shouldReturnCapacitiesInDescendingOrderWhenIsAscIsTrueAndOrderByTechnologyCount() {
        // Given
        int page = 0;
        int size = 10;
        boolean isAsc = true;
        List<Capacity> expectedCapacities = List.of(
                new Capacity(2L, "BCapacity", "Desc2", Collections.emptyList()),
                new Capacity(1L, "ACapacity", "Desc1", Collections.nCopies(3, new Technology(1L, "Java", "A programming language")))
        );
        when(capacityServicePort.findAllCapacities(page, size, CapacityOrderByOption.TECHNOLOGY_COUNT, isAsc)).thenReturn(expectedCapacities);
        List<FindCapacityResponse> expectedResponses = expectedCapacities.stream()
                .map(capacityRequestMapper::modelToFindResponse)
                .toList();

        // When
        ResponseEntity<List<FindCapacityResponse>> responseEntity = capacityRestControllerAdapter.findAllCapacities(page, size, "technology_count", isAsc);

        // Then
        verify(capacityServicePort, times(1)).findAllCapacities(page, size, CapacityOrderByOption.TECHNOLOGY_COUNT, isAsc);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedResponses, responseEntity.getBody());

    }

    @Test
    void shouldReturnCapacitiesInDescendingOrderWhenIsAscIsFalseAndOrderByTechnologyCount() {
        // Given
        int page = 0;
        int size = 10;
        boolean isAsc = false;
        List<Capacity> expectedCapacities = List.of(
                new Capacity(1L, "ACapacity", "Desc1", Collections.nCopies(3, new Technology(1L, "Java", "A programming language"))),
                new Capacity(2L, "BCapacity", "Desc2", Collections.emptyList())
        );
        when(capacityServicePort.findAllCapacities(page, size, CapacityOrderByOption.TECHNOLOGY_COUNT, isAsc)).thenReturn(expectedCapacities);
        List<FindCapacityResponse> expectedResponses = expectedCapacities.stream()
                .map(capacityRequestMapper::modelToFindResponse)
                .toList();

        // When
        ResponseEntity<List<FindCapacityResponse>> responseEntity = capacityRestControllerAdapter.findAllCapacities(page, size, "technology_count", isAsc);

        // Then
        verify(capacityServicePort, times(1)).findAllCapacities(page, size, CapacityOrderByOption.TECHNOLOGY_COUNT, isAsc);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedResponses, responseEntity.getBody());
    }
}
