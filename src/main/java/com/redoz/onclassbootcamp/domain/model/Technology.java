package com.redoz.onclassbootcamp.domain.model;

import com.redoz.onclassbootcamp.domain.util.DomainConstants;

import static java.util.Objects.requireNonNull;

public class Technology {
    private final Long id;
    private final String name;
    private final String description;

    public Technology(Long id, String name, String description) {
        this.id = id;
        this.name = requireNonNull(name, DomainConstants.FIELD_NAME_NULL_MESSAGE);
        this.description = description;
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

}
