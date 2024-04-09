package com.redoz.onclass.adapters.driving.http.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class FindVersionResponse {
    private final Long id;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final int maxNumOfStudents;
    private final FindVersionBootcampItem bootcamp;
}
