package com.rick.ormplugin.infrastructure.po;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Max.Tu
 * @program orm-plugin
 * @description
 * @create 2022-07-19 21:21
 **/
public class Service extends Base{

    private Dao dao;

    public Service(String comment, String name, Dao dao) {
        super(comment, name);
        this.dao = dao;
    }

    @Override
    public Set<String> getImports() {
        Set<String> importSet = new HashSet<>();
        importSet.add(dao.getPackage() + "." + dao.getSimpleName());
        importSet.add(dao.getModel().getPackage() + "." + dao.getModel().getSimpleName());
        return importSet;
    }

    public Dao getDao() {
        return dao;
    }
}
