package com.redoz.onclass.adapters.driving.http.controller;

import com.redoz.onclass.adapters.driving.http.dto.request.CreateCapacityRequest;
import com.redoz.onclass.adapters.driving.http.mapper.ICapacityRequestMapper;
import com.redoz.onclass.domain.api.ICapacityServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

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
}
