package com.company.input;

import com.company.services.BookingService;
import com.company.services.CancellationService;
import com.company.services.ReportingService;
import com.company.services.SearchService;

import java.io.IOException;
import java.util.Scanner;

public abstract class Input {

    protected Scanner scanner;
    protected BookingService bookingService;
    protected CancellationService cancellationService;
    protected SearchService searchService;
    protected ReportingService reportingService;

    public Input() {
        scanner = InputScanner.getInstance();
        bookingService = BookingService.getInstance();
        cancellationService = CancellationService.getInstance();
        searchService = SearchService.getInstance();
        reportingService = ReportingService.getInstance();
    }

    public abstract void flow() throws IOException;

    public static void blankLine() {
        System.out.println("======================================================================================");
    }
}
