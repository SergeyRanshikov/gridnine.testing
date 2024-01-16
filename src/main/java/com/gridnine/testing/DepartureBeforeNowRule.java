package com.gridnine.testing;

import java.time.LocalDateTime;

class DepartureBeforeNowRule implements FlightFilterRule {
    @Override
    public boolean pass(Flight flight) {
        LocalDateTime now = LocalDateTime.now();
        return flight.getSegments().stream()
                .noneMatch(segment -> segment.getDepartureDate().isBefore(now));
    }
}
