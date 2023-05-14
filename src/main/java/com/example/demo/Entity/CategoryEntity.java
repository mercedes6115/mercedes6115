package com.example.demo.Entity;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;
import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
@Entity
/*
Entity는 순수하게 DB를 생성하고 DB하고만 소통하게 하기 위해 DTO구현
DTO는 비지니스 로직에서 사용하기 위해 구현
*/

public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (nullable = false)
    private String branch;

    private String code;

    private String name;

    /*
    @ManyToOne (fetch = FetchType.LAZY)
    JPA연관관계에서 'xToOne' (ManyToOne, OneToOne)은 fetchType.EAGER가 default다.
    지연 참조를 사용해줘야 n+1등 jpa 사용에 지장이 없다.
    습관적으로 fetch에 모두 LAZY를 걸어주자 !! 😆
     */
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name ="parent_cagegory_id")
    private CategoryEntity parentCategory;

    @OneToMany(mappedBy = "parentCategory", cascade = CascadeType.ALL)
    private List<CategoryEntity> subCategory = new ArrayList<>();

    private Integer level;

    @Builder
    public CategoryEntity(String branch, String code, String name, Integer level,CategoryEntity parentCategory) {
        this.branch = branch;
        this.code = code;
        this.name = name;
        this.level = level;
        this.parentCategory = parentCategory;
    }
}

/*
한 행에 있는 값을 같은 행에 있는 다른 값과 비교해야 할 때가 생기고는 합니다.
이를 해결하려면 자기 자신 테이블을 조인하여 같은 행의 데이터 값을 가지고 비교하는 방법밖에는 없기 때문입니다.
또 기억할 점은, 셀프 조인을 할 경우에는 꼭 테이블에 별칭을 주어야 한다는 것입니다.
그렇지 않으면, 같은 테이블 내의 열을 가지고 데이터를 다루는 것이기 때문에 SQL이 어떤 행에서 데이터를 불러와야 할지 인식하지 못하기 때문입니다
 */