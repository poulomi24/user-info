package com.udemy.practice.userinfo.service;

import com.udemy.practice.userinfo.dto.UserDTO;
import com.udemy.practice.userinfo.entity.User;
import com.udemy.practice.userinfo.mapper.UserMapper;
import com.udemy.practice.userinfo.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepo userRepo;

    public UserDTO addUser(UserDTO userDTO) {
        User user = userRepo.save(UserMapper.INSTANCE.mapUserDTOToUser(userDTO));
        return UserMapper.INSTANCE.mapUserToUserDTO(user);
    }

    public ResponseEntity<UserDTO> fetchUserDetailsById(Integer id) {
        Optional<User> user = userRepo.findById(id);
        if (user.isPresent())
            return new ResponseEntity<>(UserMapper.INSTANCE.mapUserToUserDTO(user.get()), HttpStatus.OK);
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
}
