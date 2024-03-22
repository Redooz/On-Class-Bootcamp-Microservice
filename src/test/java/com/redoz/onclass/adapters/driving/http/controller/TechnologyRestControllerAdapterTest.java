package com.redoz.onclass.adapters.driving.http.controller;

import com.redoz.onclass.adapters.driving.http.dto.request.CreateTechnologyRequest;
import com.redoz.onclass.adapters.driving.http.dto.response.FindTechnologyResponse;
import com.redoz.onclass.adapters.driving.http.mapper.ITechnologyRequestMapper;
import com.redoz.onclass.domain.api.ITechnologyServicePort;
import com.redoz.onclass.domain.model.Technology;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class TechnologyRestControllerAdapterTest {

    @Mock
    private ITechnologyServicePort technologyServicePort;

    @Mock
    private ITechnologyRequestMapper technologyRequestMapper;

    private TechnologyRestControllerAdapter technologyRestControllerAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        technologyRestControllerAdapter = new TechnologyRestControllerAdapter(technologyServicePort, technologyRequestMapper);
    }

    @Test
    void shouldReturnCreatedWhenTechnologyIsCreated() {
        CreateTechnologyRequest createTechnologyRequest = new CreateTechnologyRequest("Java", "A programming language");
        Technology technology = new Technology(1L, "Java", "A programming language");
        when(technologyRequestMapper.createRequestToModel(createTechnologyRequest)).thenReturn(technology);

        ResponseEntity<Void> responseEntity = technologyRestControllerAdapter.createTechnology(createTechnologyRequest);

        verify(technologyServicePort, times(1)).saveTechnology(technology);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
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
        when(technologyServicePort.findAllTechnologies(page, size, isAsc)).thenReturn(expectedTechnologies);
        List<FindTechnologyResponse> expectedResponses = expectedTechnologies.stream()
                .map(technologyRequestMapper::modelToFindResponse)
                .toList();

        // When
        ResponseEntity<List<FindTechnologyResponse>> responseEntity = technologyRestControllerAdapter.findAllTechnologies(page, size, isAsc);

        // Then
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedResponses, responseEntity.getBody());
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
        when(technologyServicePort.findAllTechnologies(page, size, isAsc)).thenReturn(expectedTechnologies);
        List<FindTechnologyResponse> expectedResponses = expectedTechnologies.stream()
                .map(technologyRequestMapper::modelToFindResponse)
                .toList();

        // When
        ResponseEntity<List<FindTechnologyResponse>> responseEntity = technologyRestControllerAdapter.findAllTechnologies(page, size, isAsc);

        // Then
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedResponses, responseEntity.getBody());
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
        when(technologyServicePort.findAllTechnologies(page, size, isAsc)).thenReturn(expectedTechnologies);
        List<FindTechnologyResponse> expectedResponses = expectedTechnologies.stream()
                .map(technologyRequestMapper::modelToFindResponse)
                .toList();

        // When
        ResponseEntity<List<FindTechnologyResponse>> responseEntity = technologyRestControllerAdapter.findAllTechnologies(page, size, isAsc);

        // Then
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedResponses, responseEntity.getBody());
    }

}
