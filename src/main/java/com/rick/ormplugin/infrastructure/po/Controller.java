package com.rick.ormplugin.infrastructure.po;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Max.Tu
 * @program orm-plugin
 * @description
 * @create 2022-07-20 19:38
 **/
public class Controller extends Base{

    private Service service;
    private Model model;

    public Controller(String comment, String name, Service service) {
        super(comment, name);
        this.service = service;
        this.model = service.getDao().getModel();
    }

    @Override
    public Set<String> getImports() {
        Set<String> importSet = new HashSet<>();
        importSet.add(service.getPackage() + "." + service.getSimpleName());
        importSet.add(model.getPackage() + "." + model.getSimpleName());
        return importSet;
    }

    public Service getService() {
        return service;
    }

    public Model getModel() {
        return model;
    }
}
