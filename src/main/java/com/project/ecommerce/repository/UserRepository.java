package com.project.ecommerce.repository;

import com.project.ecommerce.entity.ProductEntity;
import com.project.ecommerce.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {
}
