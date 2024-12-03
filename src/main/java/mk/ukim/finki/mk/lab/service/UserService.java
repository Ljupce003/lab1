package mk.ukim.finki.mk.lab.service;

import mk.ukim.finki.mk.lab.model.EventBooking;
import mk.ukim.finki.mk.lab.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> getAll();

    Optional<User> addUser(String name);

    Optional<User> addUserBooking(Long user_id,EventBooking booking);

    Optional<User> getUserById(Long id);

    List<User> getUsersByName(String name);

    Optional<User> findUserByName(String name);


}
