package com.example.s.Controller;

import com.example.s.API.ApiResponse;
import com.example.s.Model.User;
import com.example.s.Service.AuthService;
import com.example.s.Service.MyUserDetailsService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vi/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity  login( String username, String password){

        return ResponseEntity.ok().body(" ");
    }


    @PostMapping("/register")
    public ResponseEntity  register(@RequestBody @Valid User user){
        authService.register(user);
        return ResponseEntity.ok().body(user);
    }

    @PutMapping("/Update/{userName}")
    public ResponseEntity UpdateUser(@PathVariable String userName, @RequestBody @Valid User user){
        authService.updateUser(userName,user);
        return ResponseEntity.ok().body(new ApiResponse("User Update"));

    }

    @PostMapping("/logOut")
    public ResponseEntity  logOut(){
        return ResponseEntity.ok().body(" ");
    }

    @GetMapping("/get")
    public ResponseEntity getCustomer(){
        return ResponseEntity.status(200).body(authService.getUser());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable Integer id){
       authService.deleteUser(id);
        return ResponseEntity.ok().body(new ApiResponse("User Deleted"));

    }

}
