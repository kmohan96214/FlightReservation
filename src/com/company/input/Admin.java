package com.company.input;

import com.company.models.Airline;
import com.company.models.Flight;
import com.company.models.FlightStatus;
import com.company.models.Report;

import java.time.LocalDateTime;
import java.util.Scanner;

/*
    ADMIN flow
    1. add a new airline
    2. add a new flight for a airline
    3. generate reports
        a. based on last how many days
        b. based on src and dst

    Can be extended by getting username and password stored encrypted in a db or even a csv file
 */

public class Admin extends Input {

    public Admin() {
        super();
    }

    @Override
    public void flow() {
        Input.blankLine();
        System.out.println("Hello Admin please select an option number :");
        System.out.println("1. Add Airline \n2.Add Flight \n3.Generate Report");
        Integer option = scanner.nextInt();
        Input.blankLine();

        switch (option) {
            case 1:
                addAirline();
                break;
            case 2:
                addFlight();
                break;
            case 3: generate();
                break;
            default:
                break;
        }

    }

    // REPORT GENERATION METHOD BY DEFAULT RETURN LAST 30 BOOKINGS
    public void generate() {
        System.out.println("Enter option number for the report 1.last _ days 2.between airports ");
        Integer input = scanner.nextInt();
        switch (input) {
            case 1:
                System.out.println("Enter number of days required ? ");
                Integer days = scanner.nextInt();
                System.out.println(generateReport(days));
                break;
            case 2:
                System.out.println("Enter src and dst : (BOM,DEL,MAA,BLR,GOI,CCU,HYD,TRV,JAI,BBI,IDR)");
                String src = scanner.next();
                String dst = scanner.next();
                System.out.println(generateReport(src, dst));
                break;
            default:
                System.out.println(generateReport(30));
                break;
        }
    }

    // ADDING NEW AIRLINE
    public void addAirline() {
        Input.blankLine();
        System.out.println("Enter the following details in the same order :");
        System.out.println("1. id\n2. name");
        Airline airline = new Airline();
        airline.setId(scanner.nextInt());
        airline.setName(scanner.nextLine());
        airline.save();
        Input.blankLine();
    }

    // ADDING NEW FLIGHT
    public void addFlight() {
        Scanner scanner = InputScanner.getInstance();
        Input.blankLine();
        System.out.println("Enter the following details in the same order : ");
        System.out.println("1. flight id\n2. airline id\n3.source\n4.destination\n5.departure datetime(YYYY-MM-DDTHH:MM:SS)\n" +
                "6.duration\n7.status(ACTIVE, UNKNOWN, LANDED, DIVERTED, REDIRECTED, CANCELLED)" +
                "\n8.seats\n9.available seats\n10.price");
        Flight flight = new Flight();
        flight.setId(scanner.nextInt());
        flight.setAirlineId(scanner.nextInt());
        flight.setSrc(scanner.next());
        flight.setDst(scanner.next());
        flight.setDepartureTime(LocalDateTime.parse(scanner.next()));
        flight.setDuration(scanner.next());
        flight.setStatus(Enum.valueOf(FlightStatus.class, scanner.next()));
        flight.setSeats(scanner.nextInt());
        flight.setAvailableSeats(scanner.nextInt());
        flight.setPrice(scanner.nextDouble());
        flight.save();
        Input.blankLine();
    }


    // HELPER FUNCTIONS FOR REPORT GENERATION
    public Report generateReport(Integer days) {
        return super.reportingService.generateReport(days);
    }

    public Report generateReport(String src, String dst) {
        return super.reportingService.generateReport(src, dst);
    }

}
