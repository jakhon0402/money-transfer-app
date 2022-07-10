package uz.pdp.moneytransferapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Service
public class MyAuthService implements UserDetailsService {

    @Autowired
    PasswordEncoder passwordEncoder;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<User> userList = new ArrayList<>(
                Arrays.asList(
                        new User("login",passwordEncoder.encode("parol"),new ArrayList<>()),
                        new User("red",passwordEncoder.encode("parol"),new ArrayList<>()),
                        new User("blue",passwordEncoder.encode("parol"),new ArrayList<>())
                )
        );
        for (User user : userList) {
            if(user.getUsername().equals(username)){
                return user;
            }
        }
        throw new UsernameNotFoundException("User topilmadi !");

    }
}
