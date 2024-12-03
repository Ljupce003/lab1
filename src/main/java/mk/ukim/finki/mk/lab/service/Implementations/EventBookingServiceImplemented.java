package mk.ukim.finki.mk.lab.service.Implementations;

import mk.ukim.finki.mk.lab.model.EventBooking;
import mk.ukim.finki.mk.lab.repository.jpa.EventBookingRepository;
import mk.ukim.finki.mk.lab.service.EventBookingService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventBookingServiceImplemented implements EventBookingService {

    private final EventBookingRepository eventBookingRepository;

    public EventBookingServiceImplemented(EventBookingRepository inmemoryeventbookingRepository) {
        this.eventBookingRepository = inmemoryeventbookingRepository;
    }

    @Override
    public EventBooking placeBooking(String eventName, String attendeeName, String attendeeAddress, Long numberOfTickets) {
        EventBooking eventBooking=new EventBooking(eventName,attendeeName,attendeeAddress,numberOfTickets);
        return this.eventBookingRepository.save(eventBooking);
    }

    @Override
    public List<EventBooking> getAllBookings() {
        return this.eventBookingRepository.findAll();
    }
}
