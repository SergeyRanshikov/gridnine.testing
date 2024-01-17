package com.gridnine.testing;

import java.time.LocalDateTime;

/**
 * Правило, гарантирующее, что все полетные сегменты имеют дату м время вылета, которая не раньше текущего времени.
 * Rule that ensures all flight segments have a departure date that is not before the current time.
 */
class DepartureBeforeNowRule implements FlightFilterRule {
    /**
     * Метод pass проверяет, что все сегменты в рейсе имеют дату вылета,
     * которая не раньше текущего момента времени.
     * Checks that each segment in the flight has a departure date that is not before the current time.
     *
     * @param flight the flight containing the segments to be checked.
     * @return true if all segments have a departure date on or after the current time, false if any have a departure date before the current time.
     */

    @Override
    public boolean pass(Flight flight) {
        LocalDateTime now = LocalDateTime.now();
        for (Segment segment : flight.getSegments()) {
            if (segment.getDepartureDate().isBefore(now)) {
                return false;
            }
        }
        return true;
    }
}
