package com.example.demo.DTO;

import com.example.demo.Entity.CategoryEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;
import java.util.stream.Collectors;
/*
Entity는 순수하게 DB를 생성하고 DB하고만 소통하게 하기 위해 DTO구현
DTO는 비지니스 로직에서 사용하기 위해 구현
Entity에서 list로 담은 children을 DTO에서는 Map으로 구현 (stream, Collectors 사용)
 */

/*
Entity 클래스에서는 subCategory를 list 타입으로 생성하였다.
하지만 Service 로직에서 get 메소드를 통해 front에 json형식으로 데이터를 넘길때, Map 구조로 넘겨달라는 요구 사항이
있었기에 DTO에서 stream을 통해 변환시킬 예정이다.
 */
@Getter
@Setter
@NoArgsConstructor
public class CategoryDTO {

    private Long categoryId;
    private String branch;
    private String code;
    private String name;
    private String parentCategoryName;
    private Integer level;
    private Map<String, CategoryDTO> children;

    public CategoryDTO (CategoryEntity categoryEntity) {

        this.categoryId = categoryEntity.getId();
        this.branch = categoryEntity.getBranch();
        this.code = categoryEntity.getCode();
        this.name = categoryEntity.getName();
        this.level = categoryEntity.getLevel();
        if(categoryEntity.getParentCategory() == null) {
            this.parentCategoryName = "대분류";
        } else {
            this.parentCategoryName = categoryEntity.getParentCategory().getName();
        }

        /*
        스트림은 '데이터의 흐름’입니다.
        배열 또는 컬렉션 인스턴스에 함수 여러 개를 조합해서 원하는 결과를 필터링하고 가공된 결과를 얻을 수 있습니다.
        또한 람다를 이용해서 코드의 양을 줄이고 간결하게 표현할 수 있습니다.
        즉, 배열과 컬렉션을 함수형으로 처리할 수 있습니다.
         */
        this.children = categoryEntity.getSubCategory() == null ? null :
                categoryEntity.getSubCategory().stream().collect(Collectors.toMap(
                        CategoryEntity::getName, CategoryDTO::new
        ));
    }

    public CategoryEntity toEntity () {
        return CategoryEntity.builder()
                .branch(branch)
                .code(code)
                .level(level)
                .name(name)
                .build();
    }
}
