package edu.eci.ieti.bookingsystem.controller.auth;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.eci.ieti.bookingsystem.exception.InvalidCredentialsException;
import edu.eci.ieti.bookingsystem.repository.user.UserDocument;
import edu.eci.ieti.bookingsystem.security.jwt.JwtUtils;
import edu.eci.ieti.bookingsystem.service.user.UsersService;

@RestController
@RequestMapping("/v1/auth")
public class AuthController {

    private final UsersService usersService;
    private final JwtUtils jwtUtils;

    public AuthController(UsersService usersService, JwtUtils jwtUtils) {
        this.usersService = usersService;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping
    public ResponseEntity<TokenDto> login(@RequestBody LoginDto loginDto) {
        Optional<UserDocument> optionalUser = usersService.findByEmail(loginDto.getEmail());
        if (optionalUser.isPresent()) {
            UserDocument user = optionalUser.get();
            if (BCrypt.checkpw(loginDto.getPassword(), user.getPasswordHash())) {
                TokenDto tokenDto = jwtUtils.generateAccessToken(user.getEmail());
                return ResponseEntity.ok(tokenDto);
            } else {
                throw new InvalidCredentialsException();
            }
        } else {
            throw new InvalidCredentialsException();
        }
    }
}
