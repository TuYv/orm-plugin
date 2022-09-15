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
public class Model extends Base {

    private String tableName;
    private List<Field> fields;

    public Model(String comment, String name, String tableName, List<Field> fields) {
        super(comment, name);
        this.tableName = tableName;
        this.fields = fields;
    }

    public String getTableName() {
        return tableName;
    }

    public List<Field> getFields() {
        return fields;
    }

    @Override
    public Set<String> getImports() {
        Set<String> imports = new HashSet<>();
        List<Field> fields = getFields();
        for (Field field : fields) {
            if (field.isImport()) {
                imports.add(field.getTypeName());
            }
        }
        return imports;
    }
}
