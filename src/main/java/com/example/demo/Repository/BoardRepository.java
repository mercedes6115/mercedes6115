package com.example.demo.Repository;

import com.example.demo.Entity.BoardEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {



    Integer deleteByIdAndWriter(Long boardId,String writer);

    Page<BoardEntity> findAll(Pageable pageable);
}
