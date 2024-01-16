package com.gridnine.testing;

class ArrivalBeforeDepartureRule implements FlightFilterRule {
    @Override
    public boolean pass(Flight flight) {
        return flight.getSegments().stream()
                .noneMatch(segment -> segment.getArrivalDate().isBefore(segment.getDepartureDate()));
    }
}
