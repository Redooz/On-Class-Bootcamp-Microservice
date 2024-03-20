package com.redoz.onclass.domain.model;

import com.redoz.onclass.domain.util.DomainConstants;

import java.util.List;

import static java.util.Objects.requireNonNull;

public class Technology {
    private final Long id;
    private final String name;
    private final String description;
    private final List<Capacity> capacities;

    public Technology(Long id, String name, String description, List<Capacity> capacities) {
        this.id = id;
        this.name = requireNonNull(name, DomainConstants.FIELD_NAME_NULL_MESSAGE);
        this.description = description;
        this.capacities = capacities;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<Capacity> getCapacities() {
        return capacities;
    }
}
