package mk.ukim.finki.mk.lab.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Location {
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    @Column
    private String name;
    @Column
    private String address;
    @Column
    private String capacity;
    @Column
    private String description;

    @OneToMany(mappedBy = "location")
    List<Event> eventList;

    public Location(String name,
                    String address,
                    String capacity,
                    String description) {
        this.name = name;
        this.address = address;
        this.capacity = capacity;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Location{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", capacity='" + capacity + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
