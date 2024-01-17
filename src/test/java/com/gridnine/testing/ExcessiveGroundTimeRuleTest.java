package com.gridnine.testing;

import org.junit.Test;
import java.time.LocalDateTime;
import static org.junit.Assert.*;

import org.junit.Assert;

import java.util.List;


public class ExcessiveGroundTimeRuleTest {
    @Test
    public void excessiveGroundTimeRulePasses() {
        ExcessiveGroundTimeRule rule = new ExcessiveGroundTimeRule(2);
        LocalDateTime now = LocalDateTime.now();
        Flight flight = new FlightBuilder().createFlight(
                now, now.plusHours(1),
                now.plusHours(3), now.plusHours(4)
        );
        assertTrue(rule.pass(flight));
    }

    @Test
    public void excessiveGroundTimeRuleFails() {
        ExcessiveGroundTimeRule rule = new ExcessiveGroundTimeRule(1);
        LocalDateTime now = LocalDateTime.now();
        Flight flight = new FlightBuilder().createFlight(
                now, now.plusHours(1),
                now.plusHours(3), now.plusHours(4)
        );
        assertFalse(rule.pass(flight));
    }
    @Test
    public void whenGroundTimeIsLessThanMax_ShouldPass() {
        long maxGroundTimeHours = 2L;
        ExcessiveGroundTimeRule rule = new ExcessiveGroundTimeRule(maxGroundTimeHours);
        LocalDateTime now = LocalDateTime.now();
        Flight flight = new Flight(List.of(
                new Segment(now, now.plusHours(1)),
                new Segment(now.plusHours(1).plusMinutes(30), now.plusHours(2))
        ));

        Assert.assertTrue(rule.pass(flight));
    }

    @Test
    public void whenGroundTimeEqualsMax_ShouldPass() {
        long maxGroundTimeHours = 2L;
        ExcessiveGroundTimeRule rule = new ExcessiveGroundTimeRule(maxGroundTimeHours);
        LocalDateTime now = LocalDateTime.now();
        Flight flight = new Flight(List.of(
                new Segment(now, now.plusHours(1)),
                new Segment(now.plusHours(3), now.plusHours(5))
        ));

        Assert.assertTrue(rule.pass(flight));
    }

    @Test
    public void whenGroundTimeExceedsMax_ShouldNotPass() {

        long maxGroundTimeHours = 2L;
        ExcessiveGroundTimeRule rule = new ExcessiveGroundTimeRule(maxGroundTimeHours);
        LocalDateTime now = LocalDateTime.now();
        Flight flight = new Flight(List.of(
                new Segment(now, now.plusHours(1)),
                new Segment(now.plusHours(4), now.plusHours(5))
        ));

        Assert.assertFalse(rule.pass(flight));
    }

    @Test
    public void whenFlightHasOnlyOneSegment_ShouldPass() {

        long maxGroundTimeHours = 2L;
        ExcessiveGroundTimeRule rule = new ExcessiveGroundTimeRule(maxGroundTimeHours);
        LocalDateTime now = LocalDateTime.now();
        Flight flight = new Flight(List.of(
                new Segment(now, now.plusHours(3))
        ));
        Assert.assertTrue(rule.pass(flight));
    }

    @Test
    public void whenFlightHasNoSegments_ShouldPass() {

        long maxGroundTimeHours = 2L;
        ExcessiveGroundTimeRule rule = new ExcessiveGroundTimeRule(maxGroundTimeHours);
        Flight flight = new Flight(List.of());

        Assert.assertTrue(rule.pass(flight));
    }
}
