package com.redoz.onclassbootcamp.adapters.driving.http.controller;

import com.redoz.onclassbootcamp.adapters.driving.http.dto.request.CreateCapacityRequest;
import com.redoz.onclassbootcamp.adapters.driving.http.dto.response.FindCapacityResponse;
import com.redoz.onclassbootcamp.adapters.driving.http.mapper.ICapacityRequestMapper;
import com.redoz.onclassbootcamp.adapters.driving.http.utils.FindAllCapacitiesConstants;
import com.redoz.onclassbootcamp.adapters.driving.http.utils.FindAllConstants;
import com.redoz.onclassbootcamp.domain.api.ICapacityServicePort;
import com.redoz.onclassbootcamp.domain.model.Capacity;
import com.redoz.onclassbootcamp.domain.util.CapacityOrderByOption;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/capacities/")
@RequiredArgsConstructor
@Tag(name = "Capacity", description = "The Capacities Endpoint")
public class CapacityRestControllerAdapter {
    private final ICapacityServicePort capacityServicePort;
    private final ICapacityRequestMapper capacityRequestMapper;

    @PostMapping("")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Capacity created"),
            @ApiResponse(responseCode = "400", description = "Insufficient or excessive technologies"),
            @ApiResponse(responseCode = "400", description = "Duplicate technologies"),
    })
    @Operation(summary = "Create a new capacity", description = "Create a new capacity", tags = { "Capacity" })
    public ResponseEntity<Void> createCapacity(@RequestBody @Valid CreateCapacityRequest createCapacityRequest) {
        capacityServicePort.saveCapacity(capacityRequestMapper.toModel(createCapacityRequest));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Capacities found"),
            @ApiResponse(responseCode = "404", description = "Capacities not found"),
    })
    @Operation(summary = "Get all capacities", description = "Get all capacities", tags = { "Capacity" })
    public ResponseEntity<List<FindCapacityResponse>> findAllCapacities(
            @RequestParam(defaultValue = FindAllConstants.DEFAULT_PAGE) int page,
            @RequestParam(defaultValue = FindAllConstants.DEFAULT_SIZE) int size,
            @RequestParam(defaultValue = FindAllCapacitiesConstants.DEFAULT_SORT_BY) String orderBy,
            @RequestParam(defaultValue = FindAllConstants.DEFAULT_IS_ASC) boolean isAsc) {
        List<Capacity> capacities = capacityServicePort.findAllCapacities(page, size, CapacityOrderByOption.valueOf(orderBy.toUpperCase()), isAsc);
        List<FindCapacityResponse> responseList = capacities.stream().map(capacityRequestMapper::modelToFindResponse).toList();

        return ResponseEntity.ok().body(responseList);
    }
}