package com.example.entity;

import com.example.entity.mapperclass.BaseEntity;
import jakarta.persistence.Entity;
<<<<<<< HEAD
=======
import jakarta.persistence.OneToMany;
>>>>>>> origin/main
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

<<<<<<< HEAD
=======
import java.util.List;

>>>>>>> origin/main
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CarModel extends BaseEntity {
<<<<<<< HEAD
    private String model_name;
=======
    @OneToMany(mappedBy = "carModel")
    private List<Car> carModel;
>>>>>>> origin/main
}
