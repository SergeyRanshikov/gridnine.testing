package com.gridnine.testing;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Flight> flights = FlightBuilder.createFlights();

        /*
        Создаем оригинальный список рейсов
         */
        System.out.println("Original list of flights:");
        flights.forEach(System.out::println);

        /*
        Создаем и применяем фильтр для вылетов до текущего момента времени
         */
        FlightFilter departureBeforeNowFilter = new FlightFilter(List.of(new DepartureBeforeNowRule()));
        List<Flight> afterCurrentTimeFlights = departureBeforeNowFilter.filterFlights(flights);
        System.out.println("\nFlights with departure after the current time:");
        afterCurrentTimeFlights.forEach(System.out::println);

        /*
         Создаем и применяем фильтр для сегментов с датой прилёта раньше даты вылета
         */
        FlightFilter arrivalBeforeDepartureFilter = new FlightFilter(List.of(new ArrivalBeforeDepartureRule()));
        List<Flight> validArrivalDepartureFlights = arrivalBeforeDepartureFilter.filterFlights(flights);
        System.out.println("\nFlights with arrival date after departure date:");
        validArrivalDepartureFlights.forEach(System.out::println);

        /*
         Создаем и применяем фильтр для перелетов, где общее время на земле превышает два часа
         */
        FlightFilter excessiveGroundTimeFilter = new FlightFilter(List.of(new ExcessiveGroundTimeRule(2)));
        List<Flight> lessGroundTimeFlights = excessiveGroundTimeFilter.filterFlights(flights);
        System.out.println("\nFlights with total ground time less than or equal to 2 hours:");
        lessGroundTimeFlights.forEach(System.out::println);
    }
}