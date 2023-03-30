package com.example.entity;

import com.example.entity.mapperclass.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Permission extends BaseEntity {
    private String title;
    private String activity;

    @OneToMany(mappedBy = "permission_id")
    private List<Role> roles;

}
