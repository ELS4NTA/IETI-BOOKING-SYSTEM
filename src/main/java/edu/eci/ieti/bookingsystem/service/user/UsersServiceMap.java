package edu.eci.ieti.bookingsystem.service.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import edu.eci.ieti.bookingsystem.exception.UserNotFoundException;
import edu.eci.ieti.bookingsystem.repository.user.User;

@Service
public class UsersServiceMap implements UsersService {

    Map<String, User> users = new HashMap<>();

    @Override
    public User save(User user) {
        users.put(user.getId(), user);
        return user;
    }

    @Override
    public Optional<User> findById(String id) {
        if (!users.containsKey(id))
            throw new UserNotFoundException(id);
        User user = users.get(id);
        return Optional.of(user);
    }

    @Override
    public List<User> all() {
        List<User> userList = (List<User>) users.values();
        return userList;
    }

    @Override
    public void deleteById(String id) {
        if (!users.containsKey(id))
            throw new UserNotFoundException(id);
        users.remove(id);

    }

    @Override
    public User update(User user, String userId) {
        if (!users.containsKey(userId))
            throw new UserNotFoundException(userId);
        users.put(userId, user);
        return user;
    }

}
