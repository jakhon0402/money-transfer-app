package uz.pdp.moneytransferapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import uz.pdp.moneytransferapp.payload.LoginDto;
import uz.pdp.moneytransferapp.security.JwtProvider;
import uz.pdp.moneytransferapp.service.MyAuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    JwtProvider jwtProvider;

    @Lazy
    @Autowired
    MyAuthService myAuthService;

    @Autowired
    AuthenticationManager authenticationManager;

    @GetMapping("/logout")
    public String getLogout(){
        return "Logout!";
    }

    @PostMapping("/login")
    public HttpEntity<?> loginToSystem(@RequestBody LoginDto loginDto) {
//        UserDetails userDetails = myAuthService.loadUserByUsername(loginDto.getUsername());
//        boolean exists = userDetails.getPassword().equals(loginDto.getPassword());
//        if(exists){
//            String token = jwtProvider.generateToken(loginDto.getUsername());
//            return ResponseEntity.ok(token);
//        }
//        return ResponseEntity.status(401).body("Login yoki parol xato");
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
               loginDto.getUsername(), loginDto.getPassword()
            ));
            String token = jwtProvider.generateToken(loginDto.getUsername());
            return ResponseEntity.ok(token);
        } catch (BadCredentialsException e){
            return ResponseEntity.status(401).body("Login yoki parol xato");
        }
    }
}
