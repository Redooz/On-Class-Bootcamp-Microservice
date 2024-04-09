package com.redoz.onclassbootcamp.adapters.driving.http.controller;

import com.redoz.onclassbootcamp.adapters.driving.http.dto.request.CreateBootcampRequest;
import com.redoz.onclassbootcamp.adapters.driving.http.dto.response.FindBootcampResponse;
import com.redoz.onclassbootcamp.adapters.driving.http.mapper.IBootcampRequestMapper;
import com.redoz.onclassbootcamp.adapters.driving.http.utils.FindAllBootcampsConstants;
import com.redoz.onclassbootcamp.adapters.driving.http.utils.FindAllConstants;
import com.redoz.onclassbootcamp.domain.api.IBootcampServicePort;
import com.redoz.onclassbootcamp.domain.model.Bootcamp;
import com.redoz.onclassbootcamp.domain.util.BootcampOrderByOption;
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
@RequestMapping("/bootcamps/")
@RequiredArgsConstructor
@Tag(name = "Bootcamp", description = "The Bootcamps Endpoint")
public class BootcampRestControllerAdapter {
    private final IBootcampServicePort bootcampServicePort;
    private final IBootcampRequestMapper bootcampRequestMapper;

    @PostMapping("")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Bootcamp created"),
            @ApiResponse(responseCode = "400", description = "Insufficient or excessive capacities"),
    })
    @Operation(summary = "Create a new bootcamp", description = "Create a new bootcamp", tags = { "Bootcamp" })
    public ResponseEntity<Void> createBootcamp(@RequestBody @Valid CreateBootcampRequest createBootcampRequest) {
        bootcampServicePort.saveBootcamp(bootcampRequestMapper.toModel(createBootcampRequest));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Bootcamps found"),
            @ApiResponse(responseCode = "404", description = "Bootcamps not found"),
    })
    @Operation(summary = "Get all bootcamps", description = "Get all bootcamps", tags = { "Bootcamp" })
    public ResponseEntity<List<FindBootcampResponse>> findAllBootcamps(
            @RequestParam(defaultValue = FindAllConstants.DEFAULT_PAGE) int page,
            @RequestParam(defaultValue = FindAllConstants.DEFAULT_SIZE) int size,
            @RequestParam(defaultValue = FindAllBootcampsConstants.DEFAULT_SORT_BY) String orderBy,
            @RequestParam(defaultValue = FindAllConstants.DEFAULT_IS_ASC) boolean isAsc) {
        List<Bootcamp> bootcamps = bootcampServicePort.findAllBootcamps(page, size, BootcampOrderByOption.valueOf(orderBy.toUpperCase()), isAsc);
        List<FindBootcampResponse> responseList = bootcampRequestMapper.modelToFindResponseList(bootcamps);

        return ResponseEntity.ok().body(responseList);
    }
}