package mk.ukim.finki.mk.lab.web.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Value;
import mk.ukim.finki.mk.lab.service.EventService;
import mk.ukim.finki.mk.lab.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@AllArgsConstructor
public class Custom_Controller {
    private LocationService LocationServiceImplemented;
    private EventService EventServiceImplemented;

}
