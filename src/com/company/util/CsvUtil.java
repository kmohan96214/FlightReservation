package com.company.util;

import com.company.models.Booking;
import com.company.models.Flight;
import com.company.models.Trip;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/*

    Major Heavy lifting I/O
    saves all the models to persistent CSV files
    Read as well to search for flight based on the processed data from search service

 */

public class CsvUtil {
    public Boolean write(String fileName, String row) {
        try {
            FileWriter fileWriter = new FileWriter(fileName, true);
            fileWriter.append("\n" + row);
            fileWriter.flush();
            fileWriter.close();
            return Boolean.TRUE;
        } catch (IOException exception) {
            System.out.println("error occured");
            return Boolean.FALSE;
        }
    }

    public List<Flight> searchBySrcAndDst(String src, String dst, LocalDateTime dateTime) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(Flight.filename));
        List<Flight> flights = new ArrayList<>();
        String row = new String();

        while ((row = reader.readLine()) != null) {
            if (row.endsWith("updated")) {
                continue;
            }
            Flight flight = Flight.fromString(row);
            if (src.equals(flight.getSrc()) && dst.equals(flight.getDst())
                    && flight.getDepartureTime().getDayOfYear() == dateTime.getDayOfYear()
                    && flight.getDepartureTime().getYear() == dateTime.getYear() ) {
                flights.add(flight);
            }
        }

        return flights;
    }

    public List<Trip> getTripsByUsername(String username) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(Trip.filename));
        List<Trip> trips = new ArrayList<>();
        String row;

        while ((row = reader.readLine()) != null ) {
            Trip trip = Trip.toTrip(row);
            if (username.equals(trip.getUsername())) {
                trips.add(trip);
            }
        }
        return trips;
    }

    public List<Booking> getBookingsByTripId(Integer tripId) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(Booking.filename));
        List<Booking> bookings = new ArrayList<>();
        String row;

        while ((row = reader.readLine()) != null ) {
            Booking booking = Booking.toBook(row);
            if (tripId.equals(booking.getTripId())) {
                bookings.add(booking);
            }
        }
        return bookings;
    }

    public Flight getFlightById(Integer id) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(Flight.filename));
        String row;
        while ( (row = reader.readLine()) != null ) {
            Flight flight = Flight.fromString(row);
            if (id.equals(flight.getId())) {
                return flight;
            }
        }
        return null;
    }

}
