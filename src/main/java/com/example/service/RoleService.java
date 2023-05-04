package com.example.service;

import com.example.entity.Role;
import com.example.entity.projection.RoleProjection;
import com.example.entity.projection.UserProjection;
import org.springframework.data.domain.Pageable;

public interface RoleService {
    Role addRole(Role role);

    Role findById(Long id);

    RoleProjection findUserProjectionById(Long id);
    RoleProjection findRoleProjectionByIdContainingIgnoreCase(String id, Pageable pageable);

}
