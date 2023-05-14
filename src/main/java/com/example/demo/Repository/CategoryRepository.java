package com.example.demo.Repository;


import com.example.demo.Entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

    Optional<CategoryEntity> findByName (String name);
    Optional<CategoryEntity> findByBranchAndName (String branch, String name);

    Boolean existsByBranchAndName(String branch, String name);

}