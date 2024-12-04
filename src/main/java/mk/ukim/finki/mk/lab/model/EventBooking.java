package mk.ukim.finki.mk.lab.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventBooking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String eventName;

    @Column
    private String attendeeName;

    @Column
    private String attendeeAddress;

    @Column
    private Long numberOfTickets;

    //Ke go natera namesto nova tabela,da kreira kolona vo ovaa tabela i tamu da se zacuva user_id
//    @ManyToOne()
//    private User user;

    public EventBooking(String eventName,
                        String attendeeName,
                        String attendeeAddress,
                        Long numberOfTickets) {
        this.eventName = eventName;
        this.attendeeName = attendeeName;
        this.attendeeAddress = attendeeAddress;
        this.numberOfTickets = numberOfTickets;
    }
}
