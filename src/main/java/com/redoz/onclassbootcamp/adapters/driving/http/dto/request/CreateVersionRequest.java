package com.redoz.onclassbootcamp.adapters.driving.http.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.redoz.onclassbootcamp.adapters.driving.http.utils.VersionConstants;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.Min;
import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class CreateVersionRequest {
    @JsonFormat(pattern = "yyyy-MM-dd")
    private final LocalDate startDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private final LocalDate endDate;

    @Min(value = 1, message = VersionConstants.MIN_NUM_OF_STUDENTS)
    private final int maxNumOfStudents;

    private final CreateVersionBootcampItem bootcamp;
}
