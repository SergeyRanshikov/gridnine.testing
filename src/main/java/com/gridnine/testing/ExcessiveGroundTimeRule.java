package com.gridnine.testing;

import java.time.LocalDateTime;
import java.util.List;

class ExcessiveGroundTimeRule implements FlightFilterRule {
    private final long maxGroundTimeHours;

    public ExcessiveGroundTimeRule(long maxGroundTimeHours) {
        this.maxGroundTimeHours = maxGroundTimeHours;
    }

    @Override
    public boolean pass(Flight flight) {
        List<Segment> segments = flight.getSegments();
        long totalGroundTime = 0;
        for (int i = 1; i < segments.size(); i++) {
            LocalDateTime arrivalPrevious = segments.get(i - 1).getArrivalDate();
            LocalDateTime departureNext = segments.get(i).getDepartureDate();
            totalGroundTime += java.time.Duration.between(arrivalPrevious, departureNext).toHours();
        }
        return totalGroundTime <= maxGroundTimeHours;
    }
}
