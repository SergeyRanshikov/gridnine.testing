package com.gridnine.testing;

/**
 * Правило, гарантирующее, что на всех сегментах рейса дата прибытия будет позже даты вылета.
 * Rule that ensures all flight segments have an arrival date after the departure date.
 */
class ArrivalBeforeDepartureRule implements FlightFilterRule {
    /**
     * Метод pass проверяет, что все сегменты в рейсе имеют дату прибытия после даты вылета.
     * Checks that all flight segments have an arrival date that occurs after the departure date.
     *
     * @param flight the flight containing the segments to be checked.
     * @return true if all segments have an arrival date after the departure date, false otherwise.
     */
    @Override
    public boolean pass(Flight flight) {
        for (Segment segment : flight.getSegments()) {
            if (segment.getArrivalDate().isBefore(segment.getDepartureDate())) {
                return false;
            }
        }
        return true;
    }
}
