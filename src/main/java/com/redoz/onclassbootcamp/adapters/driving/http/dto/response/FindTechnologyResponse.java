package com.redoz.onclassbootcamp.adapters.driving.http.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class FindTechnologyResponse {
    private final Long id;
    private final String name;
    private final String description;
}
