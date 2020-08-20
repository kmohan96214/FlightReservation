package com.company.services;

import com.company.models.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/*
    Singleton class
    Takes care of handling the booking
    1. books the ticket
    2. update all the relevant records
        a. updates flight seat availability
        b. creates trip record
        c. creates booking records
 */

public class BookingService {

    private static BookingService service = null;

    private BookingService() {
    }

    public static BookingService getInstance() {
        if (Objects.isNull(service)) {
            service = new BookingService();
        }
        return service;
    }

    public Trip book(Flight flight, List<Passenger> passengers, String username) throws IOException {
        Trip trip = new Trip(new Long(System.currentTimeMillis()).intValue(), username, TripStatus.SUCCESS,
                flight.getId(), flight.getPrice() * passengers.size());
        trip.save();

        List<Booking> bookings = new ArrayList<>();

        passengers.stream().forEach(pax -> {
            Booking booking = new Booking((new Long(System.currentTimeMillis()).intValue()),
                    trip.getId(), pax.getName(), pax.getAge(), pax.getPhoneNumber(), TripStatus.SUCCESS);
            bookings.add(booking);
        });
        bookings.forEach(booking -> booking.save());
        Flight.updateFlightSeats(flight.getId(), flight.getAvailableSeats() - passengers.size());

        return trip;

    }

}
