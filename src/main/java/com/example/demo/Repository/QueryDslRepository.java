package com.example.demo.Repository;

import com.example.demo.Entity.BoardEntity;
import com.example.demo.Entity.QBoardEntity;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
@RequiredArgsConstructor
public class QueryDslRepository {
    private final JPAQueryFactory jpaQueryFactory;

    public Page<BoardEntity> searchByKeyword(String keyword, String type, Pageable pageable) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        QBoardEntity b = QBoardEntity.boardEntity;

        if ("001".equals(type)) {
            booleanBuilder.and(b.writer.contains(keyword));
        } else if ("002".equals(type)) {
            booleanBuilder.and(b.title.contains(keyword));
        } else if("003".equals(type)){
            booleanBuilder.and(b.content.contains(keyword));
        }else{
            booleanBuilder.or(b.writer.contains(keyword).or(b.title.contains(keyword).or(b.content.contains(keyword))));
        }

        QueryResults<BoardEntity> results = jpaQueryFactory
                .select(b)
                .from(b)
                .where(booleanBuilder)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        List<BoardEntity> searchList = results.getResults();
        long total = results.getTotal();

        return new PageImpl<>(searchList, pageable, total);
    }
}
