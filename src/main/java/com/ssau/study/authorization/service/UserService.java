package com.ssau.study.authorization.service;

import com.ssau.study.authorization.dto.UserMapper;
import com.ssau.study.authorization.dto.UserPojo;
import com.ssau.study.authorization.entities.UserEntity;
import com.ssau.study.authorization.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;


//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        Optional<UserEntity> userOptional = userRepository.findByEmail(email);
//        if (userOptional.isEmpty())
//            throw new UsernameNotFoundException(email);
//
//        UserEntity userEntity = userOptional.get();
//
//        UserDetails user = User.builder()
//                .username(userEntity.getEmail())
//                .password(userEntity.getPassword())
//                .roles(userEntity.getRole().name())
//                .build();
//        return user;
//    }

    public UserEntity saveUser(UserEntity accountDTO) {
        accountDTO.setPassword(bCryptPasswordEncoder.encode(accountDTO.getPassword()));
        return userRepository.save(accountDTO);
    }

    public UserPojo getUserByEmail(String email) throws UsernameNotFoundException {
        Optional<UserEntity> userOptional = userRepository.findByEmail(email);
        if (userOptional.isEmpty())
            throw new UsernameNotFoundException(email);
        return userMapper.fromEntity(userOptional.get());
    }
}
