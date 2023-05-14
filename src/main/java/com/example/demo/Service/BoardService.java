package com.example.demo.Service;


import com.example.demo.Entity.BoardEntity;
import com.example.demo.Repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BoardService {

    private final BoardRepository boardRepository;


    public Page<BoardEntity> getAllBoards(Pageable pageable){
        return boardRepository.findAll(pageable);
    }


    public BoardEntity createBoard(BoardEntity boardEntity) {
        return boardRepository.save(boardEntity);
    }


    public BoardEntity getBoardByNum(Long boardId){ return boardRepository.findById(boardId).orElseThrow();}


    public Integer deleteByIdAndWriter(Long boardId ,String writer){
        return boardRepository.deleteByIdAndWriter(boardId,writer);
    }
}
