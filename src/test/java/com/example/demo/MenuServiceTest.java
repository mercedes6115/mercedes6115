package com.example.demo;


import com.example.demo.DTO.CategoryDTO;
import com.example.demo.Entity.CategoryEntity;
import com.example.demo.Repository.CategoryRepository;
import com.example.demo.Repository.MenuRepository;
import com.example.demo.Service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class MenuServiceTest {

    @Autowired
    CategoryService categoryService;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    MenuRepository menuRepository;

    private CategoryDTO createCategoryDTO(String testBranch, String testCode, String testName) {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setBranch(testBranch);
        categoryDTO.setCode(testCode);
        categoryDTO.setName(testName);
        return categoryDTO;
    }

    private CategoryEntity findCategory (Long savedId) {
        return categoryRepository.findById(savedId).orElseThrow(IllegalArgumentException::new);
    }

    @Test
    public void 카테고리_저장_테스트 () {
        //given
        CategoryDTO categoryDTO = createCategoryDTO("TestBranch", "TestCode", "TestName");
        Long savedId = categoryService.saveCategory(categoryDTO);

        //when
        CategoryEntity categoryEntity = findCategory(savedId);

    }

    @Test
    public void menuTest(){
        log.info("=========="+menuRepository.getMenuListByParentKey().toString());
    }

}
