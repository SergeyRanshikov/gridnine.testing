package com.gridnine.testing;

interface FlightFilterRule {
    /**
     * метод pass, принимающет объект Flight и возвращает boolean.
     * Этот метод должен определить, соответствует ли рейс заданному
     * правилу фильтрации.
     */
    boolean pass(Flight flight);
}
