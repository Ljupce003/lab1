package mk.ukim.finki.mk.lab.service.Implementations;

import mk.ukim.finki.mk.lab.model.Event;
import mk.ukim.finki.mk.lab.model.EventBooking;
import mk.ukim.finki.mk.lab.repository.EventBookingRepository;
import mk.ukim.finki.mk.lab.repository.EventRepository;
import mk.ukim.finki.mk.lab.service.EventBookingService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventBookingServiceImplemented implements EventBookingService {

    private final EventBookingRepository eventBookingRepository;

    public EventBookingServiceImplemented(EventBookingRepository eventBookingRepository) {
        this.eventBookingRepository = eventBookingRepository;
    }


    @Override
    public EventBooking placeBooking(String eventName, String attendeeName, String attendeeAddress, int numberOfTickets) {
        return this.eventBookingRepository.addBooking(eventName,attendeeName,attendeeAddress,(long )numberOfTickets);
    }

    @Override
    public List<EventBooking> getAllBookings() {
        return this.eventBookingRepository.getAllBookings();
    }
}
