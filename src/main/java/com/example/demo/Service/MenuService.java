package com.example.demo.Service;


import com.example.demo.Repository.MenuRepository;
import com.example.demo.Entity.MenuEntity;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonArrayFormatVisitor;
import com.google.gson.JsonArray;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;


    public List<MenuEntity> getMenuListByParentKey(){
        return menuRepository.getMenuListByParentKey();
    }
}
