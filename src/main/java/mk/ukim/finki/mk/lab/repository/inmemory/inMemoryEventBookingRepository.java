package mk.ukim.finki.mk.lab.repository.inmemory;


import mk.ukim.finki.mk.lab.Bootstrap.Dataholder;
import mk.ukim.finki.mk.lab.model.EventBooking;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class inMemoryEventBookingRepository {

    public inMemoryEventBookingRepository() {
    }

    public List<EventBooking> getAllBookings(){
        return Dataholder.bookings;
    }

    public EventBooking addBooking(String eventName, String attendeeName, String attendeeAddress, Long numberOfTickets){
        EventBooking eventBooking=new EventBooking( eventName,attendeeName, attendeeAddress, numberOfTickets);
        Dataholder.bookings.add(eventBooking);
        return eventBooking;
    }
}
