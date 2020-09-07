package com.example.stickytop;

/**
 * @Author: Jack Ou
 * @CreateDate: 2020/9/7 21:56
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/9/7 21:56
 * @UpdateRemark: 更新说明
 */
public class Star {

    private String name;
    private String group;

    public Star(String name, String group) {
        this.name = name;
        this.group = group;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }
}
