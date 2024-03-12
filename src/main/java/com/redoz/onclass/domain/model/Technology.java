package com.redoz.onclass.domain.model;

import com.redoz.onclass.domain.exception.EmptyFieldException;
import com.redoz.onclass.domain.exception.FieldLengthNotAllowed;
import com.redoz.onclass.domain.util.DomainConstants;

import static java.util.Objects.requireNonNull;

public class Technology {
    private final Long id;
    private final String name;
    private final String description;
    private final String direction;

    public Technology(Long id, String name, String description, String direction) {
        if (name.trim().isEmpty()) {
            throw new EmptyFieldException(DomainConstants.FIELD_NAME_NULL_MESSAGE);
        }
        if (name.length() > 50) {
            throw new FieldLengthNotAllowed(DomainConstants.FIELD_NAME_LENGTH_MESSAGE);
        }

        if (description.trim().isEmpty()) {
            throw new EmptyFieldException(DomainConstants.FIELD_DESCRIPTION_NULL_MESSAGE);
        }
        if (description.length() > 90) {
            throw new FieldLengthNotAllowed(DomainConstants.FIELD_DESCRIPTION_LENGTH_MESSAGE);
        }

        if (direction.trim().isEmpty()) {
            throw new EmptyFieldException(DomainConstants.FIELD_DIRECTION_NULL_MESSAGE);
        }

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
