package com.redoz.onclass.adapters.driving.http.controller;


import com.redoz.onclass.adapters.driving.http.dto.request.CreateBootcampRequest;
import com.redoz.onclass.adapters.driving.http.mapper.IBootcampRequestMapper;
import com.redoz.onclass.domain.api.IBootcampServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

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
}
