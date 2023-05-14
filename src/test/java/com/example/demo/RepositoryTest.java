package com.example.demo;


import com.example.demo.Entity.BoardEntity;
import com.example.demo.Repository.BoardRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class RepositoryTest {


    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void getLists(){
        log.info("===========================");
        List<BoardEntity> boardEntity = boardRepository.findAll();
        log.info("test results :" + boardEntity.toString());
        log.info("===========================");
    }
}
