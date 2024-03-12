package com.redoz.onclass.domain.model;

import com.redoz.onclass.domain.exception.EmptyFieldException;
import com.redoz.onclass.domain.exception.FieldLengthNotAllowed;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TechnologyTest {

    @Test
    void shouldCreateTechnologyWhenAllFieldsAreValid() {
        Technology technology = new Technology(1L, "Java", "A programming language", "Software Development");
        assertEquals(1L, technology.getId());
        assertEquals("Java", technology.getName());
        assertEquals("A programming language", technology.getDescription());
        assertEquals("Software Development", technology.getDirection());
    }

    @Test
    void shouldThrowEmptyFieldExceptionWhenNameIsEmpty() {
        assertThrows(EmptyFieldException.class, () -> new Technology(1L, "", "A programming language", "Software Development"));
    }

    @Test
    void shouldThrowEmptyFieldExceptionWhenDescriptionIsEmpty() {
        assertThrows(EmptyFieldException.class, () -> new Technology(1L, "Java", "", "Software Development"));
    }

    @Test
    void shouldThrowEmptyFieldExceptionWhenDirectionIsEmpty() {
        assertThrows(EmptyFieldException.class, () -> new Technology(1L, "Java", "A programming language", ""));
    }

    @Test
    void shouldThrowFieldLengthNotAllowedWhenNameIsTooLong() {
        assertThrows(FieldLengthNotAllowed.class, () -> new Technology(1L, "Java".repeat(51), "A programming language", "Software Development"));
    }

    @Test
    void shouldThrowFieldLengthNotAllowedWhenDescriptionIsTooLong() {
        assertThrows(FieldLengthNotAllowed.class, () -> new Technology(1L, "Java", "A programming language".repeat(10), "Software Development"));
    }
}