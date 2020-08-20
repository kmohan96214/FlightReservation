package com.company.services;

import com.company.models.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/*
    Report Generation Service
    Generates reports based on the query parameter
    1. based on airports
    2. based on dates
    3. any new kind of report can be added here.
 */


public class ReportingService {

    private static ReportingService service;

    private ReportingService() {
    }

    public static ReportingService getInstance() {
        if (Objects.isNull(service)) {
            service = new ReportingService();
        }
        return service;
    }

    public Report generateReport(String src, String dst) {

        List<Flight> flights = getFlightRows().stream()
                .filter(flight -> {
                    return src.equals(flight.getSrc()) && dst.equals(flight.getDst());
                })
                .collect(Collectors.toList());

        Set<Integer> flightIds = flights.stream().map(flight -> {
            return flight.getId();
        }).collect(Collectors.toSet());

        List<Trip> trips = getTripRows().stream()
                .filter(t -> flightIds.contains(t.getFlightId()))
                .collect(Collectors.toList());
        Set<Integer> tripIds = trips.stream().map(t -> t.getId()).collect(Collectors.toSet());

        List<Booking> bookings = getBookingRows().stream()
                .filter(b -> tripIds.contains(b.getTripId()))
                .collect(Collectors.toList());

        Report report = new ReportBuilder()
                            .setFrom(src).setTo(dst)
                            .setNumberOfBookings(bookings.size())
                            .setRevenue(trips.stream().reduce(0.0, (x, y) -> {
                                  return x + y.getCost();
                            }, Double::sum)).build();

        return report;
    }

    public Report generateReport(Integer days) {
        LocalDateTime dateTime = LocalDateTime.now().minusDays(days);
        List<Trip> trips = getTripRows().stream().filter(t -> t.getCreatedAt().isAfter(dateTime) ).collect(Collectors.toList());
        Set<Integer> tripIds = trips.stream().map(t -> t.getId()).collect(Collectors.toSet());
        List<Booking> bookings = getBookingRows().stream().filter(b -> tripIds.contains(b.getTripId())).collect(Collectors.toList());
        Report report = new ReportBuilder()
                .setStart(dateTime).setEnd(LocalDateTime.now())
                .setNumberOfBookings(bookings.size())
                .setRevenue(trips.stream().reduce(0.0, (x, y) -> {
                    return x + y.getCost();
                }, Double::sum)).build();

        return report;
    }


    private List<Flight> getFlightRows() {
        File file = new File(Flight.filename);
        try {
            return Files.readAllLines(file.toPath()).stream()
                    .map(line -> {
                        return Flight.fromString(line);
                    }).collect(Collectors.toList());
        } catch (IOException e) {
            System.out.println("error occured in reading flights");
            return null;
        }
    }

    private List<Trip> getTripRows() {
        File file = new File(Trip.filename);
        try {
            return Files.readAllLines(file.toPath()).stream()
                    .map(line -> {
                        return Trip.toTrip(line);
                    }).collect(Collectors.toList());
        } catch (IOException e) {
            System.out.println("error occurred in reading trips");
            return null;
        }
    }

    private List<Booking> getBookingRows() {
        File file = new File(Booking.filename);
        try {
            return Files.readAllLines(file.toPath()).stream()
                    .map(line -> {
                        return Booking.toBook(line);
                    }).collect(Collectors.toList());
        } catch (IOException e) {
            System.out.println("error occurred in reading booking");
            return null;
        }
    }

}

