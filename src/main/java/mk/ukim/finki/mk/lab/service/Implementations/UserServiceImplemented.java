package mk.ukim.finki.mk.lab.service.Implementations;

import mk.ukim.finki.mk.lab.model.EventBooking;
import mk.ukim.finki.mk.lab.model.User;
import mk.ukim.finki.mk.lab.repository.jpa.UserRepository;
import mk.ukim.finki.mk.lab.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImplemented implements UserService {
    private final UserRepository userRepository;

    public UserServiceImplemented(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAll() {
        return this.userRepository.findAll();
    }

    @Override
    public Optional<User> addUser(String name) {
        User user=new User(name);
        if(this.userRepository.getUserByName(name).isEmpty()){
            return Optional.of(this.userRepository.save(user));
        }
        else return Optional.empty();
    }

    @Override
    public Optional<User> addUserBooking(Long user_id, EventBooking booking) {
        Optional<User> optionalUser=this.userRepository.getUserById(user_id);
        if(optionalUser.isPresent()){
            User user=optionalUser.get();
            user.getBookings().add(booking);
            return Optional.of(this.userRepository.save(user));
        }
        return Optional.empty();
    }



    @Override
    public Optional<User> getUserById(Long id) {

        return this.userRepository.getUserById(id);
    }

    @Override
    public List<User> getUsersByName(String name) {
        return this.userRepository.getUsersByName(name);
    }

    @Override
    public Optional<User> findUserByName(String name) {
        return this.userRepository.getUserByName(name);
    }
}
