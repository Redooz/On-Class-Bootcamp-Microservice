package com.redoz.onclass.adapters.driving.http.controller;

import com.redoz.onclass.adapters.driving.http.dto.request.CreateCapacityRequest;
import com.redoz.onclass.adapters.driving.http.dto.response.FindCapacityResponse;
import com.redoz.onclass.adapters.driving.http.mapper.ICapacityRequestMapper;
import com.redoz.onclass.adapters.driving.http.utils.FindAllCapacitiesConstants;
import com.redoz.onclass.adapters.driving.http.utils.FindAllConstants;
import com.redoz.onclass.domain.api.ICapacityServicePort;
import com.redoz.onclass.domain.model.Capacity;
import com.redoz.onclass.domain.util.OrderByOption;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/capacities/")
@RequiredArgsConstructor
public class CapacityRestControllerAdapter {
    private final ICapacityServicePort capacityServicePort;
    private final ICapacityRequestMapper capacityRequestMapper;

    @PostMapping("")
    public ResponseEntity<Void> createCapacity(@RequestBody @Valid CreateCapacityRequest createCapacityRequest) {
        capacityServicePort.saveCapacity(capacityRequestMapper.toModel(createCapacityRequest));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("")
    public ResponseEntity<List<FindCapacityResponse>> findAllCapacities(
            @RequestParam(defaultValue = FindAllConstants.DEFAULT_PAGE) int page,
            @RequestParam(defaultValue = FindAllConstants.DEFAULT_SIZE) int size,
            @RequestParam(defaultValue = FindAllCapacitiesConstants.DEFAULT_SORT_BY) String orderBy,
            @RequestParam(defaultValue = FindAllConstants.DEFAULT_IS_ASC) boolean isAsc) {
        List<Capacity> capacities = capacityServicePort.findAllCapacities(page, size, OrderByOption.valueOf(orderBy.toUpperCase()), isAsc);
        List<FindCapacityResponse> responseList = capacities.stream().map(capacityRequestMapper::modelToFindResponse).toList();

        return ResponseEntity.ok().body(responseList);
    }
}
