package org.bobjiang.service.api;

import org.bobjiang.entity.Menu;

import java.util.List;

/**
 * @author BobJiang
 * @version 1.0
 * @date 2021-01-25 15:36
 */
public interface MenuService {
    /**
     *  得到所有菜单数据
     * @return 所有菜单数据
     */
    List<Menu> getAll();

    /**
     * 保存菜单数据
     * @param menu
     */
    void saveMenu(Menu menu);

    /**
     * 编辑菜单数据
     * @param menu
     */
    void editMenu(Menu menu);


    /**
     * 删除菜单数据
     * @param id
     */
    void removeMenuById(Integer id);
}
