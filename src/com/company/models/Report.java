package com.company.models;

import java.time.LocalDateTime;
import java.util.Map;

/*
    Report file for the admin

    Shows the following details
    1. number of bookings for the given query
    2. From airport  - Optional
    3. To airport - Optional
    4. Start time - Optional
    5. End time - Optional
    6. Number of Bookings grouped by airline - yet to be implemented
    7. Revenue

 */

public class Report {
    Integer numberOfBookings;
    LocalDateTime start;
    LocalDateTime end;
    Double revenue;
    Map<String, Integer> numberOfBookingsByAirline;
    String from;
    String to;

    public Report(Integer numberOfBookings, LocalDateTime start, LocalDateTime end, Double revenue, Map<String, Integer> numberOfBookingsByAirline, String from, String to) {
        this.numberOfBookings = numberOfBookings;
        this.start = start;
        this.end = end;
        this.revenue = revenue;
        this.numberOfBookingsByAirline = numberOfBookingsByAirline;
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "Report{" +
                "numberOfBookings=" + numberOfBookings +
                ", start=" + start +
                ", end=" + end +
                ", revenue=" + revenue +
                ", numberOfBookingsByAirline=" + numberOfBookingsByAirline +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                '}';
    }
}
