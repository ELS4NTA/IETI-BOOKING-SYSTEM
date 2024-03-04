package edu.eci.ieti.bookingsystem.service.user;

import java.util.List;
import java.util.Optional;

import edu.eci.ieti.bookingsystem.repository.user.UserDocument;

public interface UsersService {

    UserDocument save(UserDocument userDocument);

    Optional<UserDocument> findById(String id);

    Optional<UserDocument> findByEmail(String email);

    List<UserDocument> all();

    void deleteById(String id);

    UserDocument update(UserDocument userDocument, String userId);

}
