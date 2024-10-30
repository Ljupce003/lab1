package mk.ukim.finki.mk.lab.service.Implementations;

import mk.ukim.finki.mk.lab.model.Event;
import mk.ukim.finki.mk.lab.model.EventBooking;
import mk.ukim.finki.mk.lab.repository.EventRepository;
import mk.ukim.finki.mk.lab.service.EventBookingService;
import org.springframework.stereotype.Service;

@Service
public class EventBookingServiceImplemented implements EventBookingService {

    private final EventRepository eventRepository;

    public EventBookingServiceImplemented(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }


    @Override
    public EventBooking placeBooking(String eventName, String attendeeName, String attendeeAddress, int numberOfTickets) {
        //TODO

        return null;
    }
}
