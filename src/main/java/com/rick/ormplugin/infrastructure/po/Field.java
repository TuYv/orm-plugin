package com.rick.ormplugin.infrastructure.po;

import com.google.common.base.CaseFormat;
import org.apache.commons.lang.StringUtils;

/**
 * @author Max.Tu
 * @program orm-plugin
 * @description 对象中的成员
 * @create 2022-07-17 20:00
 **/
public class Field {

    private String comment;
    private String columnName;
    private Class<?> type;
    //是否为主键
    private boolean id;

    public Field(String comment, String columnName, Class<?> type) {
        this.comment = comment;
        this.columnName = columnName;
        this.type = type;
    }


    public String getComment() {
        return comment;
    }

    public String getTypeName() {
        return type.getName();
    }

    public String getTypeSimpleName() {
        return type.getSimpleName();
    }

    public String getColumnName() {
        return columnName;
    }

    public String getName() {
        String str = columnName;
        return CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, str);
    }

    public void setId(boolean id) {
        this.id = id;
    }

    public boolean isId() {
        return id;
    }

    public boolean isImport() {
        String typeName = getTypeName();
        return !type.isPrimitive() && !"java.lang".equals(StringUtils.substringBeforeLast(typeName, "."));
    }
}
