package com.nsu.agriculturemarketinfosys.service.jwt;

import com.nsu.agriculturemarketinfosys.entity.User;
import com.nsu.agriculturemarketinfosys.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Optional<User>optionalUser=userRepository.findFirstByEmail(username);
//
//        if (!optionalUser.isPresent())throw new UsernameNotFoundException("Username not found", null);
//        return new org.springframework.security.core.userdetails.User(optionalUser.get().getEmail(),optionalUser.get().getPassword()
//        ,new ArrayList<>());
//
//    }
@Override
public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<User> optionalUser = userRepository.findFirstByEmail(username);

    if (!optionalUser.isPresent()) {
        throw new UsernameNotFoundException("Username not found", null);
    }

    User user = optionalUser.get();

    // Convert UserRole to a String with the "ROLE_" prefix
    String role = "ROLE_" + user.getRole().toString();

    List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(role));

    return new org.springframework.security.core.userdetails.User(
            user.getEmail(),
            user.getPassword(),
            authorities
    );
}

}
