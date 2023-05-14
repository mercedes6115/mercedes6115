package com.example.demo.Service;


import com.example.demo.DTO.CategoryDTO;
import com.example.demo.Entity.CategoryEntity;
import com.example.demo.Repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class CategoryService {
    private final CategoryRepository categoryRepository;


    public Long saveCategory (CategoryDTO categoryDTO) {

        CategoryEntity categoryEntity = categoryDTO.toEntity();
        //대분류 등록
        if (categoryDTO.getParentCategoryName() == null) {

            //JPA 사용하여 DB에서 branch와 name의 중복값을 검사. (대분류에서만 가능)
            if (categoryRepository.existsByBranchAndName(categoryDTO.getBranch(), categoryDTO.getName())) {
                throw new RuntimeException("branch와 name이 같을 수 없습니다. ");
            }
            //orElse로 refactor
            CategoryEntity rootCategory = categoryRepository.findByBranchAndName(categoryDTO.getBranch(),"ROOT")
                    .orElseGet( () ->
                            CategoryEntity.builder()
                                    .name("ROOT")
                                    .code("ROOT")
                                    .branch(categoryDTO.getBranch())
                                    .level(0)
                                    .build()
                    );
            if (!categoryRepository.existsByBranchAndName(categoryDTO.getBranch(), "ROOT")) {
                categoryRepository.save(rootCategory);
            }
            categoryEntity.setParentCategory(rootCategory);
            categoryEntity.setLevel(1);
            //중, 소분류 등록
        } else {
            String parentCategoryName = categoryDTO.getParentCategoryName();
            CategoryEntity parentCategory = categoryRepository.findByBranchAndName(categoryDTO.getBranch(), parentCategoryName)
                    .orElseThrow(() -> new IllegalArgumentException("부모 카테고리 없음 예외"));
            categoryEntity.setLevel(parentCategory.getLevel() + 1);
            categoryEntity.setParentCategory(parentCategory);
            parentCategory.getSubCategory().add(categoryEntity);
        }

        //category.setLive(true);
        return categoryRepository.save(categoryEntity).getId();
    }

    public Map<String, CategoryDTO> getCategoryByBranch (String branch) {
        CategoryEntity categoryEntity = categoryRepository.findByBranchAndName(branch, "ROOT")
                .orElseThrow(() -> new IllegalArgumentException("찾는 대분류가 없습니다"));

        CategoryDTO categoryDTO = new CategoryDTO(categoryEntity);

        Map <String, CategoryDTO> data = new HashMap<>();
        data.put(categoryDTO.getName(), categoryDTO);

        return data;
    }


    public void deleteCategory (Long categoryId) {
        CategoryEntity categoryEntity = findCategory(categoryId);

        if (categoryEntity.getSubCategory().size() == 0) { //하위 카테고리 없을 경우
            CategoryEntity parentCategory = findCategory(categoryEntity.getParentCategory().getId());
            if (!parentCategory.getName().equals("ROOT")) { // ROOT가 아닌 다른 부모가 있을 경우
                parentCategory.getSubCategory().remove(categoryEntity);
            }
            categoryRepository.deleteById(categoryEntity.getId());
        } else { //하위 카테고리 있을 경우
            CategoryEntity parentCategory = findCategory(categoryEntity.getParentCategory().getId());
            //ROOT아닌 부모가 있을 경우
            if (!parentCategory.getName().equals("ROOT")) {
                parentCategory.getSubCategory().remove(categoryEntity);
            }
            categoryEntity.setName("Deleted category");
        }
    }



    public Long updateCategory (Long categoryId, CategoryDTO categoryDTO) {
        CategoryEntity categoryEntity = findCategory(categoryId);

        categoryEntity.setName(categoryDTO.getName());

        return categoryEntity.getId();
    }

     private CategoryEntity findCategory(Long savedId) {
            return categoryRepository.findById(savedId).orElseThrow(IllegalArgumentException::new);
    }
}
