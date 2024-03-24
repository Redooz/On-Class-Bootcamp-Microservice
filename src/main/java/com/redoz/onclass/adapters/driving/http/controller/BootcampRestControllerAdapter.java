package com.redoz.onclass.adapters.driving.http.controller;


import com.redoz.onclass.adapters.driving.http.dto.request.CreateBootcampRequest;
import com.redoz.onclass.adapters.driving.http.dto.response.FindBootcampResponse;
import com.redoz.onclass.adapters.driving.http.mapper.IBootcampRequestMapper;
import com.redoz.onclass.adapters.driving.http.utils.FindAllBootcampsConstants;
import com.redoz.onclass.adapters.driving.http.utils.FindAllCapacitiesConstants;
import com.redoz.onclass.adapters.driving.http.utils.FindAllConstants;
import com.redoz.onclass.domain.api.IBootcampServicePort;
import com.redoz.onclass.domain.model.Bootcamp;
import com.redoz.onclass.domain.util.BootcampOrderByOption;
import com.redoz.onclass.domain.util.CapacityOrderByOption;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/bootcamps/")
@RequiredArgsConstructor
public class BootcampRestControllerAdapter {
    private final IBootcampServicePort bootcampServicePort;
    private final IBootcampRequestMapper bootcampRequestMapper;

    @PostMapping("")
    public ResponseEntity<Void> createBootcamp(@RequestBody @Valid CreateBootcampRequest createBootcampRequest) {
        bootcampServicePort.saveBootcamp(bootcampRequestMapper.toModel(createBootcampRequest));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("")
    public ResponseEntity<List<FindBootcampResponse>> findAllBootcamps(
            @RequestParam(defaultValue = FindAllConstants.DEFAULT_PAGE) int page,
            @RequestParam(defaultValue = FindAllConstants.DEFAULT_SIZE) int size,
            @RequestParam(defaultValue = FindAllBootcampsConstants.DEFAULT_SORT_BY) String orderBy,
            @RequestParam(defaultValue = FindAllConstants.DEFAULT_IS_ASC) boolean isAsc) {
        List<Bootcamp> bootcamps = bootcampServicePort.findAllBootcamps(page, size, BootcampOrderByOption.valueOf(orderBy.toUpperCase()), isAsc);
        List<FindBootcampResponse> responseList = bootcamps.stream().map(bootcampRequestMapper::modelToFindResponse).toList();

        return ResponseEntity.ok().body(responseList);
    }
}
