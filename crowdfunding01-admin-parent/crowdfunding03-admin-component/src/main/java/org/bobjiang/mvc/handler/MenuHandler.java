package org.bobjiang.mvc.handler;

import com.bobjiang.crowd.util.ResultEntity;
import org.bobjiang.entity.Menu;
import org.bobjiang.service.api.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author BobJiang
 * @version 1.0
 * @date 2021-01-25 15:40
 */
//@Controller
//@ResponseBody
@RestController
public class MenuHandler {

    @Autowired
    MenuService menuService;

    /**
     * 得到菜单树结构数据
     * @return json
     */
    @RequestMapping("/menu/do/get.json")
    public ResultEntity<Menu> getWholeTree(){
        // 通过service层方法得到全部Menu对象的List
        List<Menu> menuList = menuService.getAll();

        // 声明一个Menu对象root，用来存放找到的根节点
        Menu root = null;

        // 使用map表示每一个菜单与id的对应关系
        Map<Integer,Menu> menuMap = new HashMap<Integer,Menu>();

        // 将菜单id与菜单对象以K-V对模式存入map
        for(Menu menu: menuList){
            menuMap.put(menu.getId(),menu);
        }

        for (Menu menu : menuList){
            Integer pid = menu.getPid();

            if (pid == null){
                // pid为null表示该menu是根节点
                root = menu;
                continue;
            }
            // 通过当前遍历的menu的pid得到其父节点
            Menu father = menuMap.get(pid);
            // 为父节点添加子节点为当前节点
            father.getChildren().add(menu);
        }

        return ResultEntity.successWithData(root);
    }

    /**
     * 新建菜单数据
     * @param menu
     * @return json
     */
    @RequestMapping("/menu/save.json")
    public ResultEntity<String> saveMenu(Menu menu){

        menuService.saveMenu(menu);

        return ResultEntity.successWithoutData();
    }

    /**
     * 编辑菜单数据
     * @param menu
     * @return
     */
    @RequestMapping("/menu/edit.json")
    public ResultEntity<String> editMenu(Menu menu){
        menuService.editMenu(menu);
        return ResultEntity.successWithoutData();
    }


    /**
     * 删除菜单数据
     * @param id
     * @return json
     */
    @RequestMapping("/menu/remove.json")
    public ResultEntity<String> removeMenu(Integer id){
        menuService.removeMenuById(id);
        return ResultEntity.successWithoutData();
    }
}
