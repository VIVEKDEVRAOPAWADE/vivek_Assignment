package com.example.carwale.model;

public class ListFields {
    private int totaldeaths;
    private  String countryname;
    private  int totalcases;
    private  int totalrecovered ;

    public int getTotaldeaths() {
        return totaldeaths;
    }

    public void setTotaldeaths(int totaldeaths) {
        this.totaldeaths = totaldeaths;
    }

    public String getCountryname() {
        return countryname;
    }

    public void setCountryname(String countryname) {
        this.countryname = countryname;
    }

    public int getTotalcases() {
        return totalcases;
    }

    @Override
    public String toString() {
        return "ListFields{" +
                "totaldeaths=" + totaldeaths +
                ", countryname='" + countryname + '\'' +
                ", totalcases=" + totalcases +
                ", totalrecovered=" + totalrecovered +
                '}';
    }

    public void setTotalcases(int totalcases) {
        this.totalcases = totalcases;
    }

    public int getTotalrecovered() {
        return totalrecovered;
    }

    public void setTotalrecovered(int totalrecovered) {
        this.totalrecovered = totalrecovered;
    }


}
