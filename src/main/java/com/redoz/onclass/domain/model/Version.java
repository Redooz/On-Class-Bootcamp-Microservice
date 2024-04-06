package com.redoz.onclass.domain.model;

import java.time.LocalDate;

public class Version {
    private final Long id;
    private final Long idBootcamp;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final int maxNumOfStudents;

    public Version(Long id, Long idBootcamp, LocalDate startDate, LocalDate endDate, int maxNumOfStudents) {
        this.id = id;
        this.idBootcamp = idBootcamp;
        this.startDate = startDate;
        this.endDate = endDate;
        this.maxNumOfStudents = maxNumOfStudents;
    }

    public Long getId() {
        return id;
    }

    public Long getIdBootcamp() {
        return idBootcamp;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public int getMaxNumOfStudents() {
        return maxNumOfStudents;
    }
}
