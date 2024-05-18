package com.redoz.onclassbootcamp.adapters.driving.http.controller;

import com.redoz.onclassbootcamp.adapters.driving.http.dto.request.CreateTechnologyRequest;
import com.redoz.onclassbootcamp.adapters.driving.http.dto.response.FindTechnologyResponse;
import com.redoz.onclassbootcamp.adapters.driving.http.mapper.ITechnologyRequestMapper;
import com.redoz.onclassbootcamp.adapters.driving.http.utils.FindAllConstants;
import com.redoz.onclassbootcamp.domain.api.ITechnologyServicePort;
import com.redoz.onclassbootcamp.domain.model.Technology;
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
@RequestMapping("/technologies/")
@RequiredArgsConstructor
@Tag(name = "Technology", description = "The Technologies Endpoint")
public class TechnologyRestControllerAdapter {
    private final ITechnologyServicePort technologyServicePort;
    private final ITechnologyRequestMapper technologyRequestMapper;

    @PostMapping("")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Technology created"),
            @ApiResponse(responseCode = "400", description = "Technology already exists"),
    })
    @Operation(summary = "Create a new technology", description = "Create a new technology", tags = { "Technology" })
    public ResponseEntity<Void> createTechnology(@RequestBody @Valid CreateTechnologyRequest createTechnologyRequest) {
        technologyServicePort.saveTechnology(technologyRequestMapper.createRequestToModel(createTechnologyRequest));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Technologies found"),
            @ApiResponse(responseCode = "404", description = "Technologies not found"),
    })
    @Operation(summary = "Get all technologies", description = "Get all technologies", tags = { "Technology" })
    public ResponseEntity<List<FindTechnologyResponse>> findAllTechnologies(
            @RequestParam(defaultValue = FindAllConstants.DEFAULT_PAGE) int page,
            @RequestParam(defaultValue = FindAllConstants.DEFAULT_SIZE) int size,
            @RequestParam(defaultValue = FindAllConstants.DEFAULT_IS_ASC) boolean isAsc) {
        List<Technology> technologies = technologyServicePort.findAllTechnologies(page, size, isAsc);
        List<FindTechnologyResponse> responseList = technologies.stream().map(technologyRequestMapper::modelToFindResponse).toList();

        return ResponseEntity.ok().body(responseList);
    }

    @GetMapping("/available")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Technologies found"),
            @ApiResponse(responseCode = "404", description = "Technologies not found"),
    })
    @Operation(summary = "Get all available technologies", description = "Get all available technologies", tags = { "Technology" })
    public ResponseEntity<List<FindTechnologyResponse>> findAllAvailableTechnologies() {
        List<Technology> technologies = technologyServicePort.findAllTechnologies(0, Integer.MAX_VALUE, true);
        List<FindTechnologyResponse> responseList = technologies.stream().map(technologyRequestMapper::modelToFindResponse).toList();

        return ResponseEntity.ok().body(responseList);
    }

    @GetMapping("/count")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Technologies count found"),
            @ApiResponse(responseCode = "404", description = "Technologies count not found"),
    })
    @Operation(summary = "Get all technologies count", description = "Get all technologies count", tags = { "Technology" })
    public ResponseEntity<Long> findAllTechnologiesCount() {
        return ResponseEntity.ok().body(technologyServicePort.findAllTechnologiesCount());
    }
}