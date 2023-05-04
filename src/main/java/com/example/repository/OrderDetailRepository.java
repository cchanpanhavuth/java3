package com.example.repository;
import com.example.entity.OrderDetail;
import com.example.entity.projection.CarProjection;
import com.example.entity.projection.OrderCarDetailProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long>{
    Optional<OrderCarDetailProjection> findByOrderId(Long id);
    Optional<OrderCarDetailProjection> findByCarId(Long id);
    List<OrderCarDetailProjection> findAllOrderDetailBy();

    Page<OrderCarDetailProjection> findAllOrderDetailProjectionBy(Pageable pageable);
}
