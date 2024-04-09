package com.redoz.onclassbootcamp.adapters.driving.http.dto.response;

import com.redoz.onclassbootcamp.adapters.driving.http.dto.CapacityTechnologyItem;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class FindCapacityResponse {
    private final Long id;
    private final String name;
    private final String description;
    private final List<CapacityTechnologyItem> technologies;
}
