package com.example.demo.Service;


import com.example.demo.Entity.UserEntity;
import com.example.demo.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserEntity signIn(UserEntity userEntity){
       return userRepository.save(userEntity);
    }

    public Long logIn(String email, String password){
        return userRepository.countByEmailAndPassword(email,password);
    }
}
