package com.rick.ormplugin.infrastructure.po;

import java.util.List;

/**
 * @author Max.Tu
 * @program orm-plugin
 * @description 表
 * @create 2022-07-17 21:12
 **/
public class Table {

    private String comment;
    private String name;
    private List<Column> columns;

    public Table(String comment, String name, List<Column> columns) {
        this.comment = comment;
        this.name = name;
        this.columns = columns;
    }

    public String getName() {
        return name;
    }

    public String getComment() {
        return comment;
    }

    public List<Column> getColumns() {
        return columns;
    }
}
