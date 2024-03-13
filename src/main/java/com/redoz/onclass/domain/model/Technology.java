package com.redoz.onclass.domain.model;

import com.redoz.onclass.domain.util.DomainConstants;

import static java.util.Objects.requireNonNull;

public class Technology {
    private final Long id;
    private final String name;
    private final String description;
    private final String direction;

    public Technology(Long id, String name, String description, String direction) {
        this.id = id;
        this.name = requireNonNull(name, DomainConstants.FIELD_NAME_NULL_MESSAGE);
        this.description = requireNonNull(description, DomainConstants.FIELD_DESCRIPTION_NULL_MESSAGE);
        this.direction = requireNonNull(direction, DomainConstants.FIELD_DIRECTION_NULL_MESSAGE);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDirection() {
        return direction;
    }

    public String getDescription() {
        return description;
    }
}
