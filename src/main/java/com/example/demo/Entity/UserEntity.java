package com.example.demo.Entity;


import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="user")
@Getter
@EntityListeners(AuditingEntityListener.class)
/*
* Entity Listener는 엔티티의 변화를 감지하고 데이블의 데이터를 조작하는 일을 한다.
* 이전에는 Column값이 수정되는 것에 대해서 반복된 코드를 추가해야했으며 개발자가 직접 추가를 하보니 실수가 발생하는 경우가 종종 발생하였다.
* 하지만 이러한 것은 EntityListener를 사용하면 쉽게 개선할 수 있다
*/
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="name")
    private String name;
    @Column(name="password")
    private String password;
    @Column(name="email")
    private String email;

    @CreatedDate
    private LocalDateTime registerDate;

}
