package com.project.ecommerce.repository;

import com.project.ecommerce.entity.OrderEntity;
import com.project.ecommerce.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity,Long> {
}
