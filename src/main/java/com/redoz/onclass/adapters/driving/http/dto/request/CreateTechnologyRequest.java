package com.redoz.onclass.adapters.driving.http.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CreateTechnologyRequest {
    private final String name;
    private final String description;
    private final String direction;
}
