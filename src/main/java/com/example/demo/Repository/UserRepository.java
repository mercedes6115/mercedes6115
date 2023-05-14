package com.example.demo.Repository;

import com.example.demo.Entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {
    public Long countByEmailAndPassword(String email, String password);
}
