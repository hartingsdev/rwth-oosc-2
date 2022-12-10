package org.rwth.swc.oosc.university.supplies;

import org.rwth.swc.oosc.university.management.Resource;
import org.rwth.swc.oosc.university.rooms.Room;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;

public abstract class Supply extends Resource { // added abstract besides the extends
    private final ArrayList<LocalDate> bookedDates = new ArrayList<>();

    private static boolean hasNotElevenConsecutiveDays(ArrayList<LocalDate> dates) {
        dates.sort(null);

        for (int i = 0; i < dates.size() - 10; i++) {
            LocalDate date = dates.get(i);
            LocalDate nextDate = date.plusDays(1);

            boolean found = true;

            for (int j = 0;  j < i + 11; j++) {
                LocalDate currentDate = dates.get(j);

                if (!currentDate.equals(nextDate)) {
                    found = false;
                    break;
                }

                nextDate = date.plusDays(1);
            }

            if (found) {
                return false;
            }
        }

        return true;
    }

    private static boolean existsBookingSunday(ArrayList<LocalDate> dates) {
        for (LocalDate date : dates) {
            if (date.getDayOfWeek() == DayOfWeek.SUNDAY) {
                return true;
            }
        }

        return false;
    }

    private boolean invariant() {
        return hasNotElevenConsecutiveDays(bookedDates) && !existsBookingSunday(bookedDates);
    }

    public boolean book(LocalDate date, Room room) {
        // Implementation for stage 4.
        // pre-conditions
        assert date != null : "pre-v: no date given";
        assert date.getDayOfWeek() != DayOfWeek.SUNDAY : "pre-v: booking day is sunday";
        assert room != null : "pre-v: no room given";
        assert room.isBooked(date) : "pre-v: room not booked";
        assert !bookedDates.contains(date) : "pre-v: supply is already booked";

        boolean result = false;

        if (room.isBooked(date) && date.getDayOfWeek() != DayOfWeek.SUNDAY) {
            if (!bookedDates.contains(date)) {
                ArrayList<LocalDate> datesPlusBooking = (ArrayList<LocalDate>) bookedDates.clone();
                datesPlusBooking.add(date);

                if (hasNotElevenConsecutiveDays(datesPlusBooking)) {
                    bookedDates.add(date);
                    result = true;
                }
            }
        }

        // post-conditions
        assert result == this.isBooked(date) : "post-v: wrong booking computation";

        assert invariant() : "cl-inv-v";
        return result;
    }
}
