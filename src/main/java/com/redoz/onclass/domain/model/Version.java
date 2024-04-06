package com.redoz.onclass.domain.model;

import java.time.LocalDate;

public class Version {
    private final Long id;
    private final Bootcamp bootcamp;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final int maxNumOfStudents;

    public Version(Long id, Bootcamp bootcamp, LocalDate startDate, LocalDate endDate, int maxNumOfStudents) {
        this.id = id;
        this.bootcamp = bootcamp;
        this.startDate = startDate;
        this.endDate = endDate;
        this.maxNumOfStudents = maxNumOfStudents;
    }

    public Long getId() {
        return id;
    }

    public Bootcamp getBootcamp() {
        return bootcamp;
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
