package com.example.demo.Controller;

import java.util.List;
import java.util.Map;

import com.example.demo.Entity.BoardEntity;
import com.example.demo.Repository.QueryDslRepository;
import com.example.demo.Service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.QuerydslJpaRepository;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ws.wsdl.wsdl11.ProviderBasedWsdl4jDefinition;
import org.thymeleaf.util.MapUtils;


@CrossOrigin(origins = "http://localhost:3000") // needed to solve this CORS problem
@RestController
@RequestMapping("/api/*") // basic url address setting -> should any requests enter this url they come here
@RequiredArgsConstructor
@Slf4j
public class BoardController {

    // adding final access modifier
    // @Autowired @RequiredArgsConstructor compare the difference
    private final BoardService boardService;
    private final QueryDslRepository querydslRepository;

    @GetMapping("/board/{page}")
    public Page<BoardEntity> getAllBoards(@PathVariable("page") int page,
                                          @RequestParam(required = false, defaultValue = "") String keyword,
                                          @RequestParam(required = false, defaultValue = "") String type,
                                          @PageableDefault(sort = "createdDate", direction = Sort.Direction.DESC) Pageable pageable){

        log.info(" keys:" + keyword+type);
        Page<BoardEntity> boardList = null;

        //검색한 결과가 있으면 타입에 해당하는 검색결과를 반환
        if (!("".equals(keyword))){
            boardList = querydslRepository.searchByKeyword(keyword, type,PageRequest.of(page,10));
        }
        // 검색 결과가 없으면 전체 목록 반환
        else {
            boardList = boardService.getAllBoards(PageRequest.of(page, 10));
        }

        return boardList;
    }

    @PostMapping("/board")
    public BoardEntity createBoard(@RequestBody BoardEntity boardEntity){
        return boardService.createBoard(boardEntity);
    }

    @GetMapping("/board/detail/{boardId}")
    public BoardEntity getBoardByNum(@PathVariable("boardId") Long boardId) {
        return boardService.getBoardByNum(boardId);
    }

    @PostMapping("/board/delete/{boardID}" )
    public Integer deleteByIdAndWriter(@PathVariable("boardID") Long boardId,@RequestBody Map<String,String> board) {
        String writer = board.get("writer");
        return boardService.deleteByIdAndWriter(boardId,writer);
    }


}
