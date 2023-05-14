package com.example.demo.Repository;

import com.example.demo.Entity.MenuEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.*;

@Repository

public interface MenuRepository extends JpaRepository<MenuEntity,Long> {

     String SQL ="WITH RECURSIVE t3 (id, menu_name, depth, parent,menu_order) AS( \n"
                    +"SELECT t1.id, t1.menu_name, t1.depth, t1.parent,t1.menu_order\n"
                    +"FROM tree_menu_view t1\n"
                    +"WHERE t1.parent = 'ROOT'\n"
                    +"UNION ALL\n"
                    +"SELECT t2.id, t2.menu_name, t2.depth, t2.parent,t2.menu_order\n"
                    +"FROM tree_menu_view t2\n"
                    +"INNER JOIN t3 ON t2.parent = t3.id)\n"
                +"SELECT * FROM t3\n"
                +"ORDER BY t3.id, t3.menu_order;";
    @Query(value=SQL,nativeQuery=true)
    public List<MenuEntity> getMenuListByParentKey();

    /*
    @Procedure(procedureName="treeMenu")
    @Transactional
    */


}
