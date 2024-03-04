package edu.eci.ieti.bookingsystem.service.user;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import edu.eci.ieti.bookingsystem.exception.UserNotFoundException;
import edu.eci.ieti.bookingsystem.repository.user.UserDocument;
import edu.eci.ieti.bookingsystem.repository.user.UserMongoRepository;

@Service
@Primary
public class UserServiceMongoDb implements UsersService {

    private final UserMongoRepository userMongoRepository;

    public UserServiceMongoDb(@Autowired UserMongoRepository userMongoRepository) {
        this.userMongoRepository = userMongoRepository;
    }

    @Override
    public UserDocument save(UserDocument userDocument) {
        return userMongoRepository.save(userDocument);
    }

    @Override
    public Optional<UserDocument> findById(String id) {
        Optional<UserDocument> user = userMongoRepository.findById(id);
        if (!user.isPresent())
            throw new UserNotFoundException(id);
        return user;
    }

    @Override
    public Optional<UserDocument> findByEmail(String email) {
        return userMongoRepository.findByEmail(email);
    }

    @Override
    public List<UserDocument> all() {
        return userMongoRepository.findAll();
    }

    @Override
    public void deleteById(String id) {
        Optional<UserDocument> user = userMongoRepository.findById(id);
        if (!user.isPresent())
            throw new UserNotFoundException(id);
        userMongoRepository.deleteById(id);
    }

    @Override
    public UserDocument update(UserDocument userDocument, String userId) {
        Optional<UserDocument> userToUpdate = userMongoRepository.findById(userId);
        if (!userToUpdate.isPresent())
            throw new UserNotFoundException(userId);
        return userMongoRepository.save(userDocument);
    }

}
