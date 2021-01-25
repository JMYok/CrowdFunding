package org.bobjiang.service.impl;

import org.bobjiang.entity.Menu;
import org.bobjiang.entity.MenuExample;
import org.bobjiang.mapper.MenuMapper;
import org.bobjiang.service.api.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author BobJiang
 * @version 1.0
 * @date 2021-01-25 15:37
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;


    public List<Menu> getAll() {
        return menuMapper.selectByExample(new MenuExample());
    }

    public void saveMenu(Menu menu) {
         menuMapper.insert(menu);
    }

    public void editMenu(Menu menu) {
        // 有选择地更新，如果menu中有的值为null（如这里的pid），则不会更新该内容
        menuMapper.updateByPrimaryKeySelective(menu);
    }

    public void removeMenuById(Integer id) {
        menuMapper.deleteByPrimaryKey(id);
    }
}
