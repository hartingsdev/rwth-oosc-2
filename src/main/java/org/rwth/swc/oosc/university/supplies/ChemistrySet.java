package org.rwth.swc.oosc.university.supplies;

import org.rwth.swc.oosc.university.rooms.Laboratory;
import org.rwth.swc.oosc.university.rooms.Room;

import java.time.LocalDate;

public class ChemistrySet {

    public void bookResource(Room room, LocalDate date) {
        throw new RuntimeException("Chemistry sets can only be booked for Laboratories.");
    }

    public void bookResource(Laboratory laboratory, LocalDate date) {
        // Book resource as normal... (not needed to implement for this exercise)
    }
}
