package com.example.entity;

import com.example.entity.mapperclass.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder

public class User extends BaseEntity {
    private String username;
    private String password;
    private String email;

    @ManyToOne
    @JoinColumn (name="role_id")
    private Role role_id;

    @OneToOne(mappedBy="user")
    private SaleStaff saleStaff;
}
