package com.redoz.onclass.adapters.driving.http.controller;

import com.redoz.onclass.adapters.driving.http.dto.request.CreateTechnologyRequest;
import com.redoz.onclass.adapters.driving.http.mapper.ITechnologyRequestMapper;
import com.redoz.onclass.domain.api.ITechnologyServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/technologies/")
@RequiredArgsConstructor
public class TechnologyRestControllerAdapter {
    private final ITechnologyServicePort technologyServicePort;
    private final ITechnologyRequestMapper technologyRequestMapper;

    @PostMapping("")
    public ResponseEntity<Void> createTechnology(@RequestBody CreateTechnologyRequest createTechnologyRequest) {
        technologyServicePort.saveTechnology(technologyRequestMapper.createRequestToModel(createTechnologyRequest));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
