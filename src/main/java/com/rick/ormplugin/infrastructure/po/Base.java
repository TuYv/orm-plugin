package com.rick.ormplugin.infrastructure.po;

import com.google.common.base.CaseFormat;
import java.util.Set;
import org.apache.commons.lang.StringUtils;

/**
 * @author Max.Tu
 * @program orm-plugin
 * @description
 * @create 2022-07-17 19:11
 **/
public abstract class Base {
    private int ormType;
    private String comment;
    private String name;
    private String author;

    public Base(String comment, String name) {
        this.comment = comment;
        this.name = name;
        this.author = System.getProperty("user.name");
    }

    public String getPackage() {
        String str = StringUtils.substringAfterLast(name, "java/");
        str = str.substring(0, str.lastIndexOf(getSimpleName()) - 1);
        return str.replaceAll("/", ".");
    }

    public abstract Set<String> getImports();

    public String getVarName() {
        return CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_CAMEL, getSimpleName());
    }

    public String getSimpleName() {
        return name.lastIndexOf("/") == -1 ? name : StringUtils.substringAfterLast(name, "/");
    }

    public void setOrmType(int ormType) {
        this.ormType = ormType;
    }

    public int getOrmType() {
        return ormType;
    }

    public String getComment() {
        return comment;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }
}
