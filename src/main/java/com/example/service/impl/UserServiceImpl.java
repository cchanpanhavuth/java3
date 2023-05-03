package com.example.service.impl;

import com.example.entity.User;
import com.example.entity.enums.StatusEnum;
import com.example.entity.projection.UserProjection;
import com.example.entity.response.Pagination;
import com.example.repository.UserRepository;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public boolean deleteById(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if(!ObjectUtils.isEmpty(user)){
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Transactional
    @Override
    public User updateUser(User user) {
        User userToUpdate = userRepository.findById(user.getId()).orElse(null);
        if(!ObjectUtils.isEmpty(user)){
            userToUpdate.setUsername(user.getUsername());
            userToUpdate.setEmail(user.getEmail());
            userToUpdate.setPassword(user.getPassword());
            return userRepository.save(userToUpdate);
        }
        return null;
    }

    @Override
    public User findById(Long id) {

        return this.userRepository.findById(id).orElse(null);
    }

    @Override
    public UserProjection findUserProjectionById(Long id) {
        return this.userRepository.findUserProjectionById(id).orElse(null);
    }

    @Override
    public List<UserProjection> findUserProjectionByUsernameContainingIgnoreCase(String username, Pagination pagination) {
        Page<UserProjection> userProjections = this.userRepository.findUserProjectionByUsernameContainingIgnoreCase(
                username, PageRequest.of(pagination.getPage()-1, pagination.getSize())
        );
        pagination.setTotalCounts(userProjections.getTotalElements());
        return userProjections.getContent() ;
    }

    @Override
    public long countUserByStatus(StatusEnum statusEnum) {
        return this.userRepository.countUserByStatus(statusEnum);
    }
}
