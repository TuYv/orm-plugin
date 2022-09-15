package com.rick.ormplugin.infrastructure.po;

/**
 * @author Max.Tu
 * @program orm-plugin
 * @description 表中的列
 * @create 2022-07-17 19:28
 **/
public class Column {

    private String comment;
    private String name;
    private int type;
    private boolean id;

    public Column(String comment, String name, int type) {
        this.comment = comment;
        this.name = name;
        this.type = type;
    }

    public String getComment() {
        return comment;
    }

    public String getName() {
        return name;
    }

    public int getType() {
        return type;
    }

    public boolean isId() {
        return id;
    }

    public void setId(boolean id) {
        this.id = id;
    }
}
