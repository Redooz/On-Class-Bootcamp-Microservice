package com.redoz.onclass.adapters.driving.http.controller;

import com.redoz.onclass.adapters.driving.http.dto.request.CreateTechnologyRequest;
import com.redoz.onclass.adapters.driving.http.mapper.ITechnologyRequestMapper;
import com.redoz.onclass.domain.api.ITechnologyServicePort;
import com.redoz.onclass.domain.model.Technology;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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
        CreateTechnologyRequest createTechnologyRequest = new CreateTechnologyRequest("Java", "A programming language", "Software Development");
        Technology technology = new Technology(1L, "Java", "A programming language", "Software Development");
        when(technologyRequestMapper.createRequestToModel(createTechnologyRequest)).thenReturn(technology);

        ResponseEntity<Void> responseEntity = technologyRestControllerAdapter.createTechnology(createTechnologyRequest);

        verify(technologyServicePort, times(1)).saveTechnology(technology);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

}
