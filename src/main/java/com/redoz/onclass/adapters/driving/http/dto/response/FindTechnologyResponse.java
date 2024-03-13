package com.redoz.onclass.adapters.driving.http.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class FindTechnologyResponse {
    private final String name;
    private final String description;
    private final String direction;
}
