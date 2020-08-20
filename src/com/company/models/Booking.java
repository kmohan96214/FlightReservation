package com.company.models;

import com.company.util.CsvUtil;

import java.util.Arrays;
import java.util.List;


/*
    Booking class
    Stores passenger details
    Linked to Trip model by trip ID
    One Trip can have multiple booking entries as any passenger cancel a trip and rest remain unchanged
    Has the following attributes:
    --- ID - unique id generated when created - UTC timestamp int value
    --- tripID - for linking to trip table
    --- paxName, number, age - user details - this can be moved to a new class Passenger if there are more details
    --- status - individual cancel status of a passenger

 */

public class Booking {
    private Integer id;
    private Integer tripId;
    private String paxName;
    private Integer age;
    private String contactNumber;
    private TripStatus status;
    public static final String filename = "booking.csv";

    public Booking(Integer id, Integer tripId, String paxName, Integer age, String contactNumber, TripStatus status) {
        this.id = id;
        this.tripId = tripId;
        this.paxName = paxName;
        this.age = age;
        this.contactNumber = contactNumber;
        this.status = status;
    }

    public Booking save() {
        CsvUtil csvUtil = new CsvUtil();
        String tosave = String.format("%s,%s,%s,%s,%s,%s", id, tripId, paxName, age, contactNumber, status);
        if (!csvUtil.write(filename, tosave)) {
            System.out.println("failed to save io error");
        }
        return this;
    }

    public static Booking toBook(String bookString) {
        List<String> split = Arrays.asList(bookString.split(","));
        Booking booking = new Booking(Integer.parseInt(split.get(0)), Integer.parseInt(split.get(1)),
                split.get(2), Integer.parseInt(split.get(3)), split.get(4), Enum.valueOf(TripStatus.class, split.get(5)));
        return booking;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTripId() {
        return tripId;
    }

    public void setTripId(Integer tripId) {
        this.tripId = tripId;
    }

    public String getPaxName() {
        return paxName;
    }

    public void setPaxName(String paxName) {
        this.paxName = paxName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public TripStatus getStatus() {
        return status;
    }

    public void setStatus(TripStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", tripId=" + tripId +
                ", paxName='" + paxName + '\'' +
                ", age=" + age +
                ", contactNumber='" + contactNumber + '\'' +
                ", status=" + status +
                '}';
    }
}
