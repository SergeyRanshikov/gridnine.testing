package com.gridnine.testing;

import org.junit.Test;
import java.time.LocalDateTime;
import static org.junit.Assert.*;

public class ArrivalBeforeDepartureRuleTest {
    @Test
    public void whenArrivalIsAfterDeparture_ShouldPass() {
        ArrivalBeforeDepartureRule rule = new ArrivalBeforeDepartureRule();
        LocalDateTime now = LocalDateTime.now();
        Flight flight = new FlightBuilder().createFlight(
                now, now.plusHours(2)
        );
        assertTrue(rule.pass(flight));
    }

    @Test
    public void whenArrivalIsBeforeDeparture_ShouldNotPass() {
        ArrivalBeforeDepartureRule rule = new ArrivalBeforeDepartureRule();
        LocalDateTime now = LocalDateTime.now();
        Flight flight = new FlightBuilder().createFlight(
                now.plusHours(2), now
        );
        assertFalse(rule.pass(flight));
    }

    @Test
    public void whenArrivalEqualsDeparture_ShouldNotPass() {
        ArrivalBeforeDepartureRule rule = new ArrivalBeforeDepartureRule();
        LocalDateTime now = LocalDateTime.now();
        Flight flight = new FlightBuilder().createFlight(
                now, now
        );
        assertTrue(rule.pass(flight));
    }
}
