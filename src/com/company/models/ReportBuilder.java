package com.company.models;

import java.time.LocalDateTime;
import java.util.Map;

/*
    Report Builder class to build Report

    As report can be based on dates or airports can optionally set any of the applicable fields

 */

public class ReportBuilder {
    Integer numberOfBookings;
    LocalDateTime start;
    LocalDateTime end;
    Double revenue;
    Map<String, Integer> numberOfBookingsByAirline;
    String from;
    String to;

    public ReportBuilder(){
    }

    public ReportBuilder setNumberOfBookings(Integer numberOfBookings) {
        this.numberOfBookings = numberOfBookings;
        return this;
    }

    public ReportBuilder setStart(LocalDateTime start) {
        this.start = start;
        return this;
    }

    public ReportBuilder setEnd(LocalDateTime end) {
        this.end = end;
        return this;
    }

    public ReportBuilder setRevenue(Double revenue) {
        this.revenue = revenue;
        return this;
    }

    public ReportBuilder setNumberOfBookingsByAirline(Map<String, Integer> numberOfBookingsByAirline) {
        this.numberOfBookingsByAirline = numberOfBookingsByAirline;
        return this;
    }

    public ReportBuilder setFrom(String from) {
        this.from = from;
        return this;
    }

    public ReportBuilder setTo(String to) {
        this.to = to;
        return this;
    }

    public Report build() {
        return new Report(numberOfBookings, start, end, revenue, numberOfBookingsByAirline, from, to);
    }
}
