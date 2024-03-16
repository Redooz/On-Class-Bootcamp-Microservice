package com.redoz.onclass.adapters.driving.http.controller;

import com.redoz.onclass.adapters.driving.http.dto.request.CreateTechnologyRequest;
import com.redoz.onclass.adapters.driving.http.dto.response.FindTechnologyResponse;
import com.redoz.onclass.adapters.driving.http.mapper.ITechnologyRequestMapper;
import com.redoz.onclass.adapters.driving.http.utils.FindAllTechnologiesConstants;
import com.redoz.onclass.domain.api.ITechnologyServicePort;
import com.redoz.onclass.domain.model.Technology;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/technologies/")
@RequiredArgsConstructor
public class TechnologyRestControllerAdapter {
    private final ITechnologyServicePort technologyServicePort;
    private final ITechnologyRequestMapper technologyRequestMapper;

    @PostMapping("")
    public ResponseEntity<Void> createTechnology(@RequestBody @Valid CreateTechnologyRequest createTechnologyRequest) {
        technologyServicePort.saveTechnology(technologyRequestMapper.createRequestToModel(createTechnologyRequest));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("")
    public ResponseEntity<List<FindTechnologyResponse>> findAllTechnologies(
            @RequestParam(defaultValue = FindAllTechnologiesConstants.DEFAULT_PAGE) int page,
            @RequestParam(defaultValue = FindAllTechnologiesConstants.DEFAULT_SIZE) int size,
            @RequestParam(defaultValue = FindAllTechnologiesConstants.DEFAULT_IS_ASC) boolean isAsc) {
        List<Technology> technologies = technologyServicePort.findAllTechnologies(page, size, isAsc);
        List<FindTechnologyResponse> responseList = technologies.stream().map(technologyRequestMapper::modelToFindResponse).toList();

        return ResponseEntity.ok().body(responseList);
    }

}
