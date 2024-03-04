package edu.eci.ieti.bookingsystem.security.jwt;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import edu.eci.ieti.bookingsystem.repository.user.UserDocument;
import edu.eci.ieti.bookingsystem.repository.user.UserMongoRepository;

@Service
public class JwtUserDetailsSerivce implements UserDetailsService {

    private final UserMongoRepository userMongoRepository;

    public JwtUserDetailsSerivce(UserMongoRepository userMongoRepository) {
        this.userMongoRepository = userMongoRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserDocument> optinalUser = userMongoRepository.findByEmail(username);
        if (optinalUser.isPresent()) {
            UserDocument user = optinalUser.get();
            List<SimpleGrantedAuthority> authorities = user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.name())).collect(Collectors.toList());
            return new User(user.getEmail(), user.getPasswordHash(), authorities);
        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }
    
}
