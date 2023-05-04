package com.example.repository;

import com.example.entity.User;
import com.example.entity.enums.StatusEnum;
import com.example.entity.projection.UserProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<UserProjection> findUserProjectionById(Long id);

    Page<UserProjection> findUserProjectionByUsernameContainingIgnoreCase(String username, Pageable pageable);


    @Query(value = "SELECT count(id) FROM User where status= :status" , nativeQuery = false)
    long countUserByStatus( @Param("status") StatusEnum statusEnum);
}
