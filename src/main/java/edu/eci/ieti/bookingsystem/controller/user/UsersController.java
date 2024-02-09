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
import edu.eci.ieti.bookingsystem.repository.user.User;
import edu.eci.ieti.bookingsystem.repository.user.UserDto;
import edu.eci.ieti.bookingsystem.service.user.UsersService;

@RestController
@RequestMapping("/v1/users/")
public class UsersController {

    private final UsersService usersService;

    public UsersController(@Autowired UsersService usersService) {
        this.usersService = usersService;
    }

    @PostMapping
    public ResponseEntity<User> createuser(@RequestBody UserDto userDto) {
        User newUser = usersService.save(new User(userDto));
        URI createdUserUri = URI.create("/v1/users/" + newUser.getId());
        return ResponseEntity.created(createdUserUri).body(newUser);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = usersService.all();
        return ResponseEntity.ok(users);
    }

    @GetMapping("{id}")
    public ResponseEntity<User> findById(@PathVariable("id") String id) {
        Optional<User> user = usersService.findById(id);
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        }
        throw new UserNotFoundException(id);
    }

    @PutMapping("{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") String id, @RequestBody UserDto userDto) {
        Optional<User> findedUser = usersService.findById(id);
        if (findedUser.isPresent()) {
            User updatedUser = findedUser.get();
            updatedUser.update(userDto);
            User newuser = usersService.save(updatedUser);
            return ResponseEntity.ok(newuser);
        }
        throw new UserNotFoundException(id);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") String id) {
        Optional<User> user = usersService.findById(id);
        if (user.isPresent()) {
            usersService.deleteById(id);
            return ResponseEntity.ok().build();
        }
        throw new UserNotFoundException(id);
    }

}
