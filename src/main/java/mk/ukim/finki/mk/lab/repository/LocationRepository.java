package mk.ukim.finki.mk.lab.repository;

import mk.ukim.finki.mk.lab.Bootstrap.Dataholder;
import mk.ukim.finki.mk.lab.model.Location;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class LocationRepository {
    public List<Location> findAll(){
        return Dataholder.locations;
    }

    public Optional<Location> findbyID(Long id){
        return Dataholder.locations.stream().filter(l -> l.getId().equals(id)).findFirst();
    }
}
