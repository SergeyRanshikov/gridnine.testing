package com.gridnine.testing;

import java.util.List;
import java.util.stream.Collectors;

class FlightFilter {
    private List<FlightFilterRule> rules;

    /**
     * Метод filterFlights принимает список рейсов и возвращает
     * только те рейсы, которые соответствуют всем правилам фильтрации.
     */

    public FlightFilter(List<FlightFilterRule> rules) {
        this.rules = rules;
    }

    public List<Flight> filterFlights(List<Flight> flights) {
        return flights.stream()
                .filter(flight -> rules.stream().allMatch(rule -> rule.pass(flight)))
                .collect(Collectors.toList());
    }
}
