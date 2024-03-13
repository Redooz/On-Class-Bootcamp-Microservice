package com.redoz.onclass.adapters.driving.http.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;


@AllArgsConstructor
@Getter
public class CreateTechnologyRequest {
    @NotBlank
    @Max(50)
    private final String name;
    
    @NotBlank
    @Max(90)
    private final String description;

    @NotBlank
    private final String direction;
}
