package com.redoz.onclass.adapters.driving.http.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class CreateVersionRequest {
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final int numOfStudents;
}
