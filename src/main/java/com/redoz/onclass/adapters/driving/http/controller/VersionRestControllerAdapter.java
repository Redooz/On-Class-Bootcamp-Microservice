package com.redoz.onclass.adapters.driving.http.controller;

import com.redoz.onclass.adapters.driving.http.dto.request.CreateVersionRequest;
import com.redoz.onclass.adapters.driving.http.dto.response.FindVersionResponse;
import com.redoz.onclass.adapters.driving.http.mapper.IVersionRequestMapper;
import com.redoz.onclass.adapters.driving.http.utils.FindAllConstants;
import com.redoz.onclass.adapters.driving.http.utils.FindAllVersionsConstants;
import com.redoz.onclass.domain.api.IVersionServicePort;
import com.redoz.onclass.domain.model.Version;
import com.redoz.onclass.domain.util.VersionOrderByOption;
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
    @Operation(summary = "Create a new version", description = "Create a new version", tags = {"Version"})
    public ResponseEntity<Void> createVersion(@RequestBody @Valid CreateVersionRequest createVersionRequest) {
        versionServicePort.saveVersion(versionRequestMapper.toModel(createVersionRequest));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Versions found"),
            @ApiResponse(responseCode = "404", description = "Versions not found"),
    })
    @Operation(summary = "Get all versions", description = "Get all versions", tags = {"Version"})
    public ResponseEntity<List<FindVersionResponse>> findAllVersions(
            @RequestParam(defaultValue = FindAllConstants.DEFAULT_PAGE) int page,
            @RequestParam(defaultValue = FindAllConstants.DEFAULT_SIZE) int size,
            @RequestParam(defaultValue = FindAllVersionsConstants.DEFAULT_SORT_BY) String orderBy,
            @RequestParam(defaultValue = FindAllConstants.DEFAULT_IS_ASC) boolean isAsc,
            @RequestParam(defaultValue = "0") long bootcampId
        ) {
        List<Version> versions = versionServicePort.findAllVersions(
                page, size, VersionOrderByOption.valueOf(orderBy.toUpperCase()), isAsc, bootcampId
        );
        List<FindVersionResponse> responseList = versionRequestMapper.modelToFindResponseList(versions);

        return ResponseEntity.ok().body(responseList);
    }

}
