package com.redoz.onclass.adapters.driving.http.controller;

import com.redoz.onclass.adapters.driving.http.dto.request.CreateVersionRequest;
import com.redoz.onclass.adapters.driving.http.mapper.IVersionRequestMapper;
import com.redoz.onclass.domain.api.IVersionServicePort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/version/")
@RequiredArgsConstructor
@Tag(name = "Version", description = "The Versions Endpoint")
public class VersionRestControllerAdapter {
    private final IVersionServicePort versionServicePort;
    private final IVersionRequestMapper versionRequestMapper;

    @PostMapping("")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Version created"),
            @ApiResponse(responseCode = "400", description = "Invalid date"),
    })
    @Operation(summary = "Create a new version", description = "Create a new version", tags = { "Version" })
    public ResponseEntity<Void> createVersion(@RequestBody @Valid CreateVersionRequest createVersionRequest) {
        versionServicePort.saveVersion(versionRequestMapper.toModel(createVersionRequest));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
