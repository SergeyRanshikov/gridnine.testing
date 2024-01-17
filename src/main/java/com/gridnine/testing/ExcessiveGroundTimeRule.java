package com.gridnine.testing;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Правило, гарантирующее, что общее время нахождения на земле между сегментами полета
 * не превышает 2-х часов.
 * Rule that ensures the total ground time between segments in a flight does not exceed a maximum threshold.
 */
class ExcessiveGroundTimeRule implements FlightFilterRule {

    private final long maxGroundTimeHours;

    /**
     * Constructor for the ExcessiveGroundTimeRule class.
     *
     * @param maxGroundTimeHours the maximum allotted ground time in hours between consecutive segments of a flight.
     */
    public ExcessiveGroundTimeRule(long maxGroundTimeHours) {
        this.maxGroundTimeHours = maxGroundTimeHours;
    }

    /**
     * Метод pass вычисляет общее время на земле для всех сегментов рейса
     * и проверяет, не превышает ли оно заданного максимума.
     * Calculates the total ground time for a flight and ensures it does not exceed the maximum threshold.
     *
     * @param flight the flight to be checked for excessive ground time.
     * @return true if the total ground time is within the allowed limit, false if it exceeds the maximum threshold.
     */
    @Override
    public boolean pass(Flight flight) {
        List<Segment> segments = flight.getSegments();
        long totalGroundTime = 0;
        for (int i = 0; i < segments.size() - 1; i++) {
            Segment currentSegment = segments.get(i);
            Segment nextSegment = segments.get(i + 1);
            LocalDateTime arrival = currentSegment.getArrivalDate();
            LocalDateTime departure = nextSegment.getDepartureDate();
            long groundTime = java.time.Duration.between(arrival, departure).toHours();
            totalGroundTime += groundTime;
        }
        return totalGroundTime <= maxGroundTimeHours;
    }
}
