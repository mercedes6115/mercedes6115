package com.example.demo.Entity;


import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Table(name="tree_menu_view")
@EntityListeners(AuditingEntityListener.class)
public class MenuEntity {

    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "MENU_NAME")
    private String menu_name;

    @Column(name = "PARENT")
    private String parent;

    @Column(name="DEPTH")
    private int depth;
    

    public void setMenu_name(String menu_name) {
        this.menu_name = menu_name;
    }
    public void setParent(String parent) {
        this.parent = parent;
    }
}
