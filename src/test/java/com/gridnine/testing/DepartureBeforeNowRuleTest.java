package com.gridnine.testing;

import org.junit.Test;
import java.time.LocalDateTime;
import static org.junit.Assert.*;

public class DepartureBeforeNowRuleTest {

    @Test
    public void whenDepartureIsAfterNow_ShouldPass() {
        DepartureBeforeNowRule rule = new DepartureBeforeNowRule();
        LocalDateTime now = LocalDateTime.now();
        Flight flight = new FlightBuilder().createFlight(
                now.plusSeconds(10), now.plusHours(2)
        );
        assertTrue(rule.pass(flight));
    }

    @Test
    public void whenDepartureIsBeforeNow_ShouldNotPass() {
        DepartureBeforeNowRule rule = new DepartureBeforeNowRule();
        LocalDateTime now = LocalDateTime.now();
        Flight flight = new FlightBuilder().createFlight(
                now.minusHours(2), now.plusHours(2)
        );
        assertFalse(rule.pass(flight));
    }

    @Test
    public void whenDepartureIsExactlyNow_ShouldPass() {
        DepartureBeforeNowRule rule = new DepartureBeforeNowRule();
        LocalDateTime now = LocalDateTime.now();
        Flight flight = new FlightBuilder().createFlight(
                now, now.plusHours(2)
        );
        assertFalse(rule.pass(flight));
    }
}
