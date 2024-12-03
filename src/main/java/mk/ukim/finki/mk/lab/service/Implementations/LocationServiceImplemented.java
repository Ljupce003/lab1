package mk.ukim.finki.mk.lab.service.Implementations;

import mk.ukim.finki.mk.lab.model.Location;
import mk.ukim.finki.mk.lab.repository.inmemory.inMemoryLocationRepository;
import mk.ukim.finki.mk.lab.repository.jpa.LocationRepository;
import mk.ukim.finki.mk.lab.service.LocationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationServiceImplemented implements LocationService {
    private final LocationRepository locationRepository;

    public LocationServiceImplemented(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @Override
    public List<Location> findAll() {
        return this.locationRepository.findAll();
    }
}
