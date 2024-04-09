package com.redoz.onclassbootcamp.adapters.driving.http.controller;

import com.redoz.onclassbootcamp.adapters.driving.http.dto.request.CreateBootcampCapacityItem;
import com.redoz.onclassbootcamp.adapters.driving.http.dto.request.CreateBootcampRequest;
import com.redoz.onclassbootcamp.adapters.driving.http.dto.response.FindBootcampCapacityItem;
import com.redoz.onclassbootcamp.adapters.driving.http.dto.response.FindBootcampResponse;
import com.redoz.onclassbootcamp.adapters.driving.http.mapper.IBootcampRequestMapper;
import com.redoz.onclassbootcamp.domain.api.IBootcampServicePort;
import com.redoz.onclassbootcamp.domain.model.Bootcamp;
import com.redoz.onclassbootcamp.domain.model.Capacity;
import com.redoz.onclassbootcamp.domain.util.BootcampOrderByOption;
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

    @Test
    void shouldReturnBootcampsInAscendingOrderWhenIsAscIsTrueAndOrderByName() {
        List<Bootcamp> bootcamps = List.of(
                new Bootcamp(1L, "ABootcamp1", "Desc1", Collections.emptyList()),
                new Bootcamp(2L, "Bootcamp2", "Desc2", Collections.emptyList()),
                new Bootcamp(3L, "CBootcamp3", "Desc3", Collections.emptyList()));
        when(bootcampServicePort.findAllBootcamps(0, 10, BootcampOrderByOption.NAME, true)).thenReturn(bootcamps);

        List<FindBootcampResponse> findBootcampResponses = List.of(
                new FindBootcampResponse(1L, "ABootcamp1", "Desc1", Collections.emptyList()),
                new FindBootcampResponse(2L, "Bootcamp2", "Desc2", Collections.emptyList()),
                new FindBootcampResponse(3L, "CBootcamp3", "Desc3", Collections.emptyList()));
        when(bootcampRequestMapper.modelToFindResponseList(bootcamps)).thenReturn(findBootcampResponses);

        ResponseEntity<List<FindBootcampResponse>> responseEntity = bootcampRestControllerAdapter.findAllBootcamps(0, 10, "name", true);

        verify(bootcampServicePort, times(1)).findAllBootcamps(0, 10, BootcampOrderByOption.NAME, true);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(findBootcampResponses, responseEntity.getBody());
    }

    @Test
    void shouldReturnBootcampsInDescendingOrderWhenIsAscIsFalseAndOrderByName() {
        List<Bootcamp> bootcamps = List.of(
                new Bootcamp(1L, "CBootcamp1", "Desc1", Collections.emptyList()),
                new Bootcamp(2L, "Bootcamp2", "Desc2", Collections.emptyList()),
                new Bootcamp(3L, "ABootcamp3", "Desc3", Collections.emptyList()));
        when(bootcampServicePort.findAllBootcamps(0, 10, BootcampOrderByOption.NAME, false)).thenReturn(bootcamps);

        List<FindBootcampResponse> findBootcampResponses = List.of(
                new FindBootcampResponse(1L, "CBootcamp1", "Desc1", Collections.emptyList()),
                new FindBootcampResponse(2L, "Bootcamp2", "Desc2", Collections.emptyList()),
                new FindBootcampResponse(3L, "ABootcamp3", "Desc3", Collections.emptyList()));
        when(bootcampRequestMapper.modelToFindResponseList(bootcamps)).thenReturn(findBootcampResponses);

        ResponseEntity<List<FindBootcampResponse>> responseEntity = bootcampRestControllerAdapter.findAllBootcamps(0, 10, "name", false);

        verify(bootcampServicePort, times(1)).findAllBootcamps(0, 10, BootcampOrderByOption.NAME, false);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(findBootcampResponses, responseEntity.getBody());
    }

    @Test
    void shouldReturnBootcampsInAscendingOrderWhenIsAscIsTrueAndOrderByCapacityCount() {
        List<Capacity> capacities1  = List.of(new Capacity(1L, "Capacity1", "Desc1", Collections.emptyList()));
        List<Capacity> capacities2 = List.of(
                new Capacity(1L, "Capacity1", "Desc1", Collections.emptyList()),
                new Capacity(2L, "Capacity2", "Desc2", Collections.emptyList()));
        List<Capacity> capacities3 = List.of(
                new Capacity(1L, "Capacity1", "Desc1", Collections.emptyList()),
                new Capacity(2L, "Capacity2", "Desc2", Collections.emptyList()),
                new Capacity(3L, "Capacity3", "Desc3", Collections.emptyList()));

        List<FindBootcampCapacityItem> findBootcampCapacityItems1 = List.of(new FindBootcampCapacityItem(1L, "Capacity1", Collections.emptyList()));
        List<FindBootcampCapacityItem> findBootcampCapacityItems2 = List.of(
                new FindBootcampCapacityItem(1L, "Capacity1", Collections.emptyList()),
                new FindBootcampCapacityItem(2L, "Capacity2", Collections.emptyList()));
        List<FindBootcampCapacityItem> findBootcampCapacityItems3 = List.of(
                new FindBootcampCapacityItem(1L, "Capacity1", Collections.emptyList()),
                new FindBootcampCapacityItem(2L, "Capacity2", Collections.emptyList()),
                new FindBootcampCapacityItem(3L, "Capacity3", Collections.emptyList()));

        List<Bootcamp> bootcamps = List.of(
                new Bootcamp(1L, "Bootcamp1", "Desc1", capacities1),
                new Bootcamp(2L, "Bootcamp2", "Desc2", capacities2),
                new Bootcamp(3L, "Bootcamp3", "Desc3", capacities3));
        when(bootcampServicePort.findAllBootcamps(0, 10, BootcampOrderByOption.CAPACITY_COUNT, true)).thenReturn(bootcamps);

        List<FindBootcampResponse> findBootcampResponses = List.of(
                new FindBootcampResponse(1L, "Bootcamp1", "Desc1", findBootcampCapacityItems1),
                new FindBootcampResponse(2L, "Bootcamp2", "Desc2", findBootcampCapacityItems2),
                new FindBootcampResponse(3L, "Bootcamp3", "Desc3", findBootcampCapacityItems3));
        when(bootcampRequestMapper.modelToFindResponseList(bootcamps)).thenReturn(findBootcampResponses);

        ResponseEntity<List<FindBootcampResponse>> responseEntity = bootcampRestControllerAdapter.findAllBootcamps(0, 10, "capacity_count", true);

        verify(bootcampServicePort, times(1)).findAllBootcamps(0, 10, BootcampOrderByOption.CAPACITY_COUNT, true);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(findBootcampResponses, responseEntity.getBody());
    }

    @Test
    void shouldReturnBootcampsInDescendingOrderWhenIsAscIsFalseAndOrderByCapacityCount() {
        List<Capacity> capacities1 = List.of(new Capacity(1L, "Capacity1", "Desc1", Collections.emptyList()));
        List<Capacity> capacities2 = List.of(
                new Capacity(1L, "Capacity1", "Desc1", Collections.emptyList()),
                new Capacity(2L, "Capacity2", "Desc2", Collections.emptyList()));
        List<Capacity> capacities3 = List.of(
                new Capacity(1L, "Capacity1", "Desc1", Collections.emptyList()),
                new Capacity(2L, "Capacity2", "Desc2", Collections.emptyList()),
                new Capacity(3L, "Capacity3", "Desc3", Collections.emptyList()));

        List<FindBootcampCapacityItem> findBootcampCapacityItems1 = List.of(new FindBootcampCapacityItem(1L, "Capacity1", Collections.emptyList()));
        List<FindBootcampCapacityItem> findBootcampCapacityItems2 = List.of(
                new FindBootcampCapacityItem(1L, "Capacity1", Collections.emptyList()),
                new FindBootcampCapacityItem(2L, "Capacity2", Collections.emptyList()));
        List<FindBootcampCapacityItem> findBootcampCapacityItems3 = List.of(
                new FindBootcampCapacityItem(1L, "Capacity1", Collections.emptyList()),
                new FindBootcampCapacityItem(2L, "Capacity2", Collections.emptyList()),
                new FindBootcampCapacityItem(3L, "Capacity3", Collections.emptyList()));

        List<Bootcamp> bootcamps = List.of(
                new Bootcamp(1L, "Bootcamp1", "Desc1", capacities1),
                new Bootcamp(2L, "Bootcamp2", "Desc2", capacities2),
                new Bootcamp(3L, "Bootcamp3", "Desc3", capacities3));
        when(bootcampServicePort.findAllBootcamps(0, 10, BootcampOrderByOption.CAPACITY_COUNT, false)).thenReturn(bootcamps);

        List<FindBootcampResponse> findBootcampResponses = List.of(
                new FindBootcampResponse(1L, "Bootcamp1", "Desc1", findBootcampCapacityItems1),
                new FindBootcampResponse(2L, "Bootcamp2", "Desc2", findBootcampCapacityItems2),
                new FindBootcampResponse(3L, "Bootcamp3", "Desc3", findBootcampCapacityItems3));
        when(bootcampRequestMapper.modelToFindResponseList(bootcamps)).thenReturn(findBootcampResponses);

        ResponseEntity<List<FindBootcampResponse>> responseEntity = bootcampRestControllerAdapter.findAllBootcamps(0, 10, "capacity_count", false);

        verify(bootcampServicePort, times(1)).findAllBootcamps(0, 10, BootcampOrderByOption.CAPACITY_COUNT, false);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(findBootcampResponses, responseEntity.getBody());
    }
}
