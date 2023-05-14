package com.example.demo.Controller;


import com.example.demo.Entity.MenuEntity;
import com.example.demo.Service.MenuService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RequestMapping("/menu/*")
@RestController
@Slf4j
@CrossOrigin
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;

    @GetMapping("/menus")
    public List<MenuEntity> getMenuListByParentKey(){
        return menuService.getMenuListByParentKey();
    }

}
