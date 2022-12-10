package org.rwth.swc.oosc.university.supplies;

import org.rwth.swc.oosc.university.management.Resource;
import org.rwth.swc.oosc.university.rooms.Room;

import java.time.LocalDate;

public abstract class Supply extends Resource { // added abstract besides the extends
    public boolean book(LocalDate date, Room room) {
        // TODO: Implement me for stage 4.
        return true;
    }
}
