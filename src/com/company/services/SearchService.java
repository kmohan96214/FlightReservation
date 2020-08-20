package com.company.services;

import com.company.models.Flight;
import com.company.util.CsvUtil;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/*
    Singleton class
    For search between airports for a given date
    Can be extended to search connected flights as well
 */

public class SearchService {

    private static SearchService searchService = null;

    private SearchService() {
    }

    public static SearchService getInstance() {
        if (Objects.isNull(searchService)) {
            searchService = new SearchService();
        }
        return searchService;
    }

    public List<Flight> getFlights(String src, String dst, LocalDateTime date) throws IOException {
        return new CsvUtil().searchBySrcAndDst(src, dst, date);
    }
}
