package com.redoz.onclass.adapters.driving.http.dto.response;

import com.redoz.onclass.adapters.driving.http.dto.CapacityTechnologyItem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FindBootcampCapacityItem {
    private Long id;
    private String name;
    private List<CapacityTechnologyItem> technologies;
}
