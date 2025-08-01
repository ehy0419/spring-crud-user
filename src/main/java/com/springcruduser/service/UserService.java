package com.springcruduser.service;

import com.springcruduser.dto.UserRequest;
import com.springcruduser.dto.UserResponse;
import com.springcruduser.entity.User;
import com.springcruduser.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public UserResponse save(UserRequest userRequest) {
        User savedUser = userRepository.save(new User(userRequest.getUsername()));
        return new UserResponse(savedUser.getId(), savedUser.getUsername());
    }

    @Transactional(readOnly = true)
    public List<UserResponse> findUsers(){
        List<User> users = userRepository.findAll();
        List<UserResponse> userResponses = new ArrayList<>();
        for (User user : users) {
            UserResponse userResponse = new UserResponse(
                    user.getId(),
                    user.getUsername()
            );
            userResponses.add(userResponse);
        }
        return userResponses;
    }

    @Transactional
    public UserResponse update(Long userId, UserRequest userRequest){
        User user = userRepository.findById(userId).orElseThrow(
                () -> new IllegalArgumentException("해당 userID " + userId + " 존재하지 않습니다.")
        );
        user.updateUsername(userRequest.getUsername());
        return new UserResponse(
                user.getId(),
                user.getUsername());
    }

    @Transactional
    public void delete(Long userId){
        boolean exists = userRepository.existsById(userId);
        if (!exists){
            throw new IllegalArgumentException("해당 userID " + userId + " 존재하지 않습니다.");
        }
        userRepository.deleteById(userId);
    }

    @Transactional(readOnly = true)
    public UserResponse findUser(Long userId){
        User user = userRepository.findById(userId).orElseThrow(
                () -> new IllegalArgumentException("해당 userID " + userId + " 존재하지 않습니다.")
        );
        return new UserResponse(user.getId(), user.getUsername());
    }
}
