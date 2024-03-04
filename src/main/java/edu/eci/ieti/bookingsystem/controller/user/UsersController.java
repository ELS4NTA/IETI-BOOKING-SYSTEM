package edu.eci.ieti.bookingsystem.controller.user;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.eci.ieti.bookingsystem.exception.UserNotFoundException;
import edu.eci.ieti.bookingsystem.repository.user.UserDocument;
import edu.eci.ieti.bookingsystem.repository.user.UserDto;
import edu.eci.ieti.bookingsystem.service.user.UsersService;

@RestController
@RequestMapping("/v1/users")
public class UsersController {

    private final UsersService usersService;

    public UsersController(@Autowired UsersService usersService) {
        this.usersService = usersService;
    }

    @PostMapping
    public ResponseEntity<UserDocument> createuser(@RequestBody UserDto userDto) {
        UserDocument newUserDocument = usersService.save(new UserDocument(userDto));
        URI createdUserUri = URI.create("/v1/users/" + newUserDocument.getId());
        return ResponseEntity.created(createdUserUri).body(newUserDocument);
    }

    @GetMapping
    public ResponseEntity<List<UserDocument>> getAllUsers() {
        List<UserDocument> userDocuments = usersService.all();
        return ResponseEntity.ok(userDocuments);
    }

    @GetMapping("{id}")
    public ResponseEntity<UserDocument> findById(@PathVariable("id") String id) {
        Optional<UserDocument> user = usersService.findById(id);
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        }
        throw new UserNotFoundException(id);
    }

    @PutMapping("{id}")
    public ResponseEntity<UserDocument> updateUser(@PathVariable("id") String id, @RequestBody UserDto userDto) {
        Optional<UserDocument> findedUser = usersService.findById(id);
        if (findedUser.isPresent()) {
            UserDocument updatedUserDocument = findedUser.get();
            updatedUserDocument.update(userDto);
            UserDocument newuser = usersService.save(updatedUserDocument);
            return ResponseEntity.ok(newuser);
        }
        throw new UserNotFoundException(id);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") String id) {
        Optional<UserDocument> user = usersService.findById(id);
        if (user.isPresent()) {
            usersService.deleteById(id);
            return ResponseEntity.ok().build();
        }
        throw new UserNotFoundException(id);
    }

}
