package edu.eci.ieti.bookingsystem.service.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import edu.eci.ieti.bookingsystem.exception.UserNotFoundException;
import edu.eci.ieti.bookingsystem.repository.user.UserDocument;

@Service
public class UsersServiceMap implements UsersService {

    Map<String, UserDocument> users = new HashMap<>();

    @Override
    public UserDocument save(UserDocument userDocument) {
        users.put(userDocument.getId(), userDocument);
        return userDocument;
    }

    @Override
    public Optional<UserDocument> findById(String id) {
        if (!users.containsKey(id))
            throw new UserNotFoundException(id);
        UserDocument userDocument = users.get(id);
        return Optional.of(userDocument);
    }

    @Override
    public Optional<UserDocument> findByEmail(String email) {
        return Optional.empty();
    }

    @Override
    public List<UserDocument> all() {
        List<UserDocument> userDocumentList = new ArrayList<>();
        userDocumentList.addAll(users.values());
        return userDocumentList;
    }

    @Override
    public void deleteById(String id) {
        if (!users.containsKey(id))
            throw new UserNotFoundException(id);
        users.remove(id);

    }

    @Override
    public UserDocument update(UserDocument userDocument, String userId) {
        if (!users.containsKey(userId))
            throw new UserNotFoundException(userId);
        users.put(userId, userDocument);
        return userDocument;
    }

}
