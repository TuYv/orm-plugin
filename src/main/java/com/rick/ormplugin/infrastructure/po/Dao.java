package com.rick.ormplugin.infrastructure.po;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Max.Tu
 * @program orm-plugin
 * @description
 * @create 2022-07-17 19:59
 **/
public class Dao extends Base{

    private Model model;

    public Dao(String comment, String name, Model model) {
        super(comment, name);
        this.model = model;
    }

    public Model getModel() {
        return model;
    }

    @Override
    public Set<String> getImports() {
        Set<String> imports = new HashSet<>();
        imports.add(model.getPackage() + "." + model.getSimpleName());
        List<Field> fields = model.getFields();
        for (Field field : fields) {
            if (field.isId() && field.isImport()) {
                imports.add(field.getTypeName());
                break;
            }
        }
        return imports;
    }
}
