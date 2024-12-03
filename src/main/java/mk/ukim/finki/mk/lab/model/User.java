package mk.ukim.finki.mk.lab.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,unique = true)
    private String name;

    //mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true
    @OneToMany()
    private List<EventBooking> bookings;

    public User(String name) {
        this.name = name;
        this.bookings=new ArrayList<>();
    }
}
