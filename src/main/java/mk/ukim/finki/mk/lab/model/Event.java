package mk.ukim.finki.mk.lab.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class Event {
    private String name;
    private String description;
    private double popularityScore;
    private Long id;
    private Location location;

    private List<EventBooking> bookings;
    public Event(String name, String description, double popularityScore, Long id, Location location) {
        this.name = name;
        this.description = description;
        this.popularityScore = popularityScore;
        this.id = id;
        this.location = location;
        this.bookings=new ArrayList<>();
    }


}
