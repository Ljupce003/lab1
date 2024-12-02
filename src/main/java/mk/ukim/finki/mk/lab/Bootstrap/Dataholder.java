package mk.ukim.finki.mk.lab.Bootstrap;


import jakarta.annotation.PostConstruct;
import mk.ukim.finki.mk.lab.model.Event;
import mk.ukim.finki.mk.lab.model.EventBooking;
import mk.ukim.finki.mk.lab.model.Location;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class Dataholder {

    public static  List<Event> events =null;

    public static List<Location> locations=null;

    private final ArrayList<Long> occupied_ids=new ArrayList<>();

    public static ArrayList<EventBooking> bookings=null;

    @PostConstruct
    private void init(){
        Random random=new Random();

        locations=new ArrayList<>(5);

        locations.add(new Location(getEvId(random),"Doma","Orce Nikolov","80","home" ));
        locations.add(new Location(getEvId(random),"Gosti","Davor","40","friend"));
        locations.add(new Location(getEvId(random),"Javno","Kame Carsija","1500","public"));
        locations.add(new Location(getEvId(random),"Koncert","Plostad","10000","public"));


        events=new ArrayList<>(10);

        events.add(new Event("Svadba","Heppy",1500,getEvId(random),locations.get(random.nextInt(0, locations.size()))));
        events.add(new Event("Slava","Heppy",900,getEvId(random),locations.get(random.nextInt(0, locations.size()))));
        events.add(new Event("Rodenden","Heppi",1050,getEvId(random),locations.get(random.nextInt(0, locations.size()))));


        bookings=new ArrayList<>(5);

        bookings.add(new EventBooking("Rodenden","Petar","Orce Nikolov", 5L));



    }

    private Long getEvId(Random random){
        boolean complete = false;
        Long returnId=null;
        while (!complete){
            Long newId = random.nextLong(1,1000);
            if(!this.occupied_ids.contains(newId)){
                returnId=newId;
                complete=true;
                this.occupied_ids.add(newId);
            }
        }
        return returnId;
    }
}
