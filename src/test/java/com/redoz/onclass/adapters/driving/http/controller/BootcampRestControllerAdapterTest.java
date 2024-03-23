package com.redoz.onclass.adapters.driving.http.controller;

import com.redoz.onclass.adapters.driving.http.dto.request.CreateBootcampCapacityItem;
import com.redoz.onclass.adapters.driving.http.dto.request.CreateBootcampRequest;
import com.redoz.onclass.adapters.driving.http.mapper.IBootcampRequestMapper;
import com.redoz.onclass.domain.api.IBootcampServicePort;
import com.redoz.onclass.domain.model.Bootcamp;
import com.redoz.onclass.domain.model.Capacity;
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

class BootcampRestControllerAdapterTest {

    @Mock
    private IBootcampServicePort bootcampServicePort;

    @Mock
    private IBootcampRequestMapper bootcampRequestMapper;

    private BootcampRestControllerAdapter bootcampRestControllerAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        bootcampRestControllerAdapter = new BootcampRestControllerAdapter(bootcampServicePort, bootcampRequestMapper);
    }

    @Test
    void shouldReturnCreatedWhenBootcampIsCreated() {
        List<CreateBootcampCapacityItem> createBootcampCapacityItems =
                List.of(new CreateBootcampCapacityItem(1L, "Capacity1"),
                        new CreateBootcampCapacityItem(2L, "Capacity2"),
                        new CreateBootcampCapacityItem(3L, "Capacity3"));
        CreateBootcampRequest createBootcampRequest = new CreateBootcampRequest("Bootcamp1", "Desc1", createBootcampCapacityItems);

        List<Capacity> capacities = List.of(new Capacity(1L, "Capacity1", "",Collections.emptyList()), new Capacity(2L, "Capacity2", "",Collections.emptyList()), new Capacity(3L, "Capacity3", "",Collections.emptyList()));
        Bootcamp bootcamp = new Bootcamp(1L, "Bootcamp1", "Desc1", capacities);
        when(bootcampRequestMapper.toModel(createBootcampRequest)).thenReturn(bootcamp);

        ResponseEntity<Void> responseEntity = bootcampRestControllerAdapter.createBootcamp(createBootcampRequest);

        verify(bootcampServicePort, times(1)).saveBootcamp(bootcamp);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

}
