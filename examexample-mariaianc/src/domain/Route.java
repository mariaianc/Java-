package domain;

import java.time.LocalTime;

public class Route {

    private int id;
    private String source_city;
    private String destination_city;
    private LocalTime departure_time;
    private LocalTime arrival_time;
    private int available_seats;
    private int price_ticket;

    public Route(int id, String source_city, String destination_city, LocalTime departure_time,
                 LocalTime arrival_time, int available_seats, int price_ticket) {
        this.id = id;
        this.source_city = source_city;
        this.destination_city = destination_city;
        this.departure_time = departure_time;
        this.arrival_time = arrival_time;
        this.available_seats = available_seats;
        this.price_ticket = price_ticket;
    }

    public int getId() {
        return id;
    }

    public String getSource_city() {
        return source_city;
    }

    public String getDestination_city() {
        return destination_city;
    }

    public LocalTime getDeparture_time() {
        return departure_time;
    }

    public LocalTime getArrival_time() {
        return arrival_time;
    }

    public int getAvailable_seats() {
        return available_seats;
    }

    public int getPrice_ticket() {
        return price_ticket;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSource_city(String source_city) {
        this.source_city = source_city;
    }

    public void setDestination_city(String destination_city) {
        this.destination_city = destination_city;
    }

    public void setDeparture_time(LocalTime departure_time) {
        this.departure_time = departure_time;
    }

    public void setArrival_time(LocalTime arrival_time) {
        this.arrival_time = arrival_time;
    }

    public void setAvailable_seats(int available_seats) {
        this.available_seats = available_seats;
    }

    public void setPrice_ticket(int price_ticket) {
        this.price_ticket = price_ticket;
    }

    @Override
    public String toString() {
        return "Route{" +
                "id=" + id +
                ", source_city='" + source_city + '\'' +
                ", destination_city='" + destination_city + '\'' +
                ", departure_time='" + departure_time + '\'' +
                ", arrival_time='" + arrival_time + '\'' +
                ", available_seats=" + available_seats +
                ", price_ticket=" + price_ticket +
                '}';
    }
}
