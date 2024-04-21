package com.example.s.Service;

import com.example.s.API.ApiException;
import com.example.s.Model.User;
import com.example.s.Repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

private final AuthRepository authRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = authRepository.findUserByUserName(username);

        if (user == null) {
           throw new ApiException("NOT found");
        }
      return  user;
    }
}
