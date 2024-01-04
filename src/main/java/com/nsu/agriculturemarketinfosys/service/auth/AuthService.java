package com.nsu.agriculturemarketinfosys.service.auth;


import com.nsu.agriculturemarketinfosys.dto.SignupRequest;
import com.nsu.agriculturemarketinfosys.dto.UserDTO;

public interface AuthService {

    UserDTO createUser(SignupRequest signupRequest);
    boolean hasUserWithEmail(String email);
}
