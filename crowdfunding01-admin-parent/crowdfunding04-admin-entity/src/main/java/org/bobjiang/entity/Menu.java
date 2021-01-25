package org.bobjiang.entity;

import java.util.ArrayList;
import java.util.List;

public class Menu {
    //主键
    private Integer id;

    //父节点id
    private Integer pid;

    //节点名称
    private String name;

    //节点url:点击跳转
    private String url;

    //节点图标
    private String icon;

    //存储子节点
    private List<Menu> Children = new ArrayList<Menu>();

    //默认节点为打开状态
    private boolean open = true;


    public Menu() {
    }

    public Menu(Integer id, Integer pid, String name, String url, String icon) {
        this.id = id;
        this.pid = pid;
        this.name = name;
        this.url = url;
        this.icon = icon;
    }

    public List<Menu> getChildren() {
        return Children;
    }

    public void setChildren(List<Menu> children) {
        Children = children;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }
}