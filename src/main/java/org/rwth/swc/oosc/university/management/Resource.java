package org.rwth.swc.oosc.university.management;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public abstract class Resource {

    protected List<LocalDate> bookedDates = new ArrayList<>();

    public boolean isBooked(LocalDate date) {
        return bookedDates.contains(date);
    }
}
