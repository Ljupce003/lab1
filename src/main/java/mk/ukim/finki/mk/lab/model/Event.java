package mk.ukim.finki.mk.lab.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Event {

    @Column
    private String name;
    @Column
    private String description;
    @Column
    private double popularityScore;
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    //So ova oznacuvame deka poveke eventi mozat da imat ista location
    @ManyToOne
    private Location location;

    //Tuka moze da se stavi deka poveke eventi mozat da imat poveke bookings
    @ManyToMany
    private List<EventBooking> bookings;

    public Event(String name,
                 String description,
                 double popularityScore,
                 Location location) {
        this.name = name;
        this.description = description;
        this.popularityScore = popularityScore;
        this.location = location;
        this.bookings=new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Event{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", popularityScore=" + popularityScore +
                ", id=" + id +
                ", location=" + location +
                '}';
    }
}
