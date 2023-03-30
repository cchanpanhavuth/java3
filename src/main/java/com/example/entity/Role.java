package com.example.entity;

import com.example.entity.mapperclass.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Role extends BaseEntity {
    private String role_description;

    @OneToMany(mappedBy = "role_id")
    private List<User> users;

    @ManyToOne
    @JoinColumn(name="permission_id")
    private Permission permission_id;

}
