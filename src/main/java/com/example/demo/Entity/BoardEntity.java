package com.example.demo.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;


@Entity
// DB에 있는 테이블 이름으로 매핑
// Board 엔티티를 테이블명 board_test로 매핑
@Table(name="board_test")
@Getter
// due to security problem DO NOT use @Setter Annotation
@EntityListeners(AuditingEntityListener.class)
public class BoardEntity {

//    @Column(name="DB컬럼명")
//    매핑할 private 변수
//
//    @Temporal(TemporalType.TIMESTAMP)
//    시간과 관련된 매핑
//    Date뿐만 아니라 LocalDateTime도 지원함
//
//    @Enumerated(EnumType.STRING)
//    Enum타입 매핑 지원함
//
//
//    @Lob
//    CLOB BLOB매핑
//    CLOB : String char[], java sql.CLOB
//    BLOB : byte[], java.sql.BLOB
//
//    @Transient
//    이 필드는 매핑하지 않는다
//    애플리케이션에서 DB에 저장하지 않는 필드


        //  인스턴스 변수 id를 DB테이블 board_test 기본키 ID로 매핑
        //    IDENTITY:DB에 위임 Mysql
        //    SEQUENCE : 시퀀스 오브젝트 사용 ORACLE
        //    TABLE : 키 생성용 테이블 사용, 모든 DB에서 사용
        //    AUTO : 방언에 따라 자동지정, 기본값
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long id;
        // long + 대체키 + 키 사용
        // AUTO_INCREMENT 사용시 생각보다 금방 끝남

        @Column(name="title")
        private String title;
        @Column(name="content")
        private String content;
        @Column(name="writer")
        private String writer;


        @CreatedDate
        private LocalDateTime createdDate;

        //  FK가 있는 클래스를 관계의 주인으로 설정 FK가 있는 클래스는 현재 클래스를 board라고 참조하고 있음
        //  @OneToMany 속성은 양뱡향 관계일때 사용 반대쪽 매핑의 필드 이름을 값으로 주면 된다.
        //  양방향 관계는 없고 서로다른 단방향 관계 2개가 로직으로 잘 묶여서 양방향인것처럼 보이는것
        //  외래키 하나만으로 양방향 연관관계를 맺는다.
        //  게시물 1개 -> 여러개의 첨부파일
        //  연관관계의 주인을 정할때 mappedBy를 사용해준다.
        //  주인은 mappedBy를 사용하지 않는다.
        //  연관관계의 주인을 설정하는 것은 외래 키 관리자를 선택하는 것
        //  연관관계의 주인은 외래키가 있는 곳으로 정해야 한다.
        //  아래의 경우 file엔티티에 board_id외래키가 있으므로 file엔티티가 관계의 주인이다.
        //  연관관계의 주인만 데이터베이스 연관관계와 매핑되고 외래 키를 관리할 수 있고, 주인이 아닌 반대편은 읽기만 가능하고 외래 키를 변경하지 못한다.
        //    @OneToMany(mappedBy = "board")
        //    private List<File> fileList;
        /*
        @JsonIgnore
        //  필드 레벨에서 무시 될 수있는 속성을 표시하는 데 사용.
        @OneToMany(mappedBy = "board")
        private List<Reply> replyList = new ArrayList<Reply>();
        */

//    public List<File> getFileList() {
//        return fileList;
//    }
/*
        public List<Reply> getReplyList() {
                return replyList;
        }
        public void addReply(Reply reply){
                reply.setBoard(this);
                this.replyList.add(reply);
        }
*/

        public void setTitle(String title) {
                this.title = title;
        }

        public void setContent(String content) {
                this.content = content;
        }

        public void setWriter(String writer) {this.writer = writer;}

}