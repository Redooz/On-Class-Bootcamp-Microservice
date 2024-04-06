package com.redoz.onclass.domain.model;

import java.util.Date;

public class Version {
    private final Long id;
    private final Long idBootcamp;
    private final Date startDate;
    private final Date endDate;
    private final int maxNumOfStudents;

    public Version(Long id, Long idBootcamp, Date startDate, Date endDate, int maxNumOfStudents) {
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

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public int getMaxNumOfStudents() {
        return maxNumOfStudents;
    }
}
