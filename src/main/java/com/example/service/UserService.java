package com.example.service;

import com.example.entity.User;
import com.example.entity.enums.StatusEnum;
import com.example.entity.projection.UserProjection;
import com.example.entity.response.Pagination;

import java.util.List;

public interface UserService {
    User addUser(User user);
    boolean deleteById(Long id);

    User updateUser(User user);

    User findById(Long id);

    UserProjection findUserProjectionById(Long id);
    List<UserProjection> findUserProjectionByUsernameContainingIgnoreCase(String username, Pagination pagination);

    long countUserByStatus(StatusEnum statusEnum);
}
