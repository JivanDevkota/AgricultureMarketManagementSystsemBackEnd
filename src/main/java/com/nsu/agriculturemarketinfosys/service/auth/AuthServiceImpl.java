package com.nsu.agriculturemarketinfosys.service.auth;

import com.nsu.agriculturemarketinfosys.dto.SignupRequest;
import com.nsu.agriculturemarketinfosys.dto.UserDTO;
import com.nsu.agriculturemarketinfosys.entity.Order;
import com.nsu.agriculturemarketinfosys.enums.OrderStatus;
import com.nsu.agriculturemarketinfosys.entity.User;
import com.nsu.agriculturemarketinfosys.enums.UserRole;
import com.nsu.agriculturemarketinfosys.repository.OrderRepository;
import com.nsu.agriculturemarketinfosys.repository.UserRepository;
//import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;


@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private OrderRepository orderRepository;

    public UserDTO createUser(SignupRequest signupRequest){

        User user=new User();
        user.setEmail(signupRequest.getEmail());
        user.setName(signupRequest.getName());
        user.setPassword(new BCryptPasswordEncoder().encode(signupRequest.getPassword()));
        user.setRole(UserRole.USER);
        User createUser=userRepository.save(user);

        Order order=new Order();
        order.setAmount(0L);
        order.setTotalAmount(0L);
        order.setDiscount(0L);
        order.setUser(createUser);
        order.setOrderStatus(OrderStatus.Pending);
        orderRepository.save(order);

        UserDTO userDTO=new UserDTO();
        userDTO.setId(createUser.getId());

        return userDTO;
    }

    public boolean hasUserWithEmail(String email){
        return userRepository.findFirstByEmail(email).isPresent();
    }

    @PostConstruct
    public void createAdminAccount(){
        User adminAccount = userRepository.findByRole(UserRole.ADMIN);
        if (null == adminAccount){
            User user=new User();
            user.setEmail("admin1@.com");
            user.setName("admin");
            user.setRole(UserRole.ADMIN);
            user.setPassword(new BCryptPasswordEncoder().encode("admin"));

            userRepository.save(user);
        }
    }


}
