package com.company.models;

import com.company.util.CsvUtil;

/*
    Airline class to store airline detials
    Can be extended by adding more details like contact, address etc
 */


public class Airline {
    private Integer id;
    private String name;
    private static final String filename = "airline.csv";

    public Airline save() {
        String tosave = String.format("%s,%s", id, name);
        CsvUtil csvUtil = new CsvUtil();
        if (!csvUtil.write(filename, tosave)) {
            System.out.println("error due to io");
        }
        return this;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Airline{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
