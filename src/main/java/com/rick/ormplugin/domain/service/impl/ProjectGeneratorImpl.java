package com.rick.ormplugin.domain.service.impl;

import com.google.common.base.CaseFormat;
import com.intellij.openapi.project.Project;
import com.rick.ormplugin.domain.model.vo.CodeGenContextVO;
import com.rick.ormplugin.domain.service.AbstractProjectGenerator;
import com.rick.ormplugin.infrastructure.po.Column;
import com.rick.ormplugin.infrastructure.po.Controller;
import com.rick.ormplugin.infrastructure.po.Dao;
import com.rick.ormplugin.infrastructure.po.Field;
import com.rick.ormplugin.infrastructure.po.Model;
import com.rick.ormplugin.infrastructure.po.Service;
import com.rick.ormplugin.infrastructure.po.Table;
import com.rick.ormplugin.infrastructure.utils.JavaType;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Max.Tu
 * @program orm-plugin
 * @description
 * @create 2022-07-17 12:06
 **/
public class ProjectGeneratorImpl extends AbstractProjectGenerator {

    @Override
    protected void generateORM(Project project, CodeGenContextVO codeGenContext) {
        List<Table> tables = codeGenContext.getTables();
        for (Table table : tables) {
            List<Column> columns = table.getColumns();
            List<Field> fields = new ArrayList<>();

            for (Column column : columns) {
                Field field = new Field(column.getComment(),  column.getName(), JavaType.convertType(column.getType()));
                field.setId(column.isId());
                fields.add(field);
            }

            Model model = new Model(table.getComment(), codeGenContext.getModelPackage() + CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, table.getName()), table.getName(), fields);
            writeFile(project, codeGenContext.getModelPackage(), model.getSimpleName() + ".java", "domain/orm/model.ftl", model);

            // 生成DAO
            Dao dao = new Dao(table.getComment(), codeGenContext.getDaoPackage() + CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, table.getName()) + "Mapper", model);
            writeFile(project, codeGenContext.getDaoPackage(), dao.getSimpleName() + ".java", "domain/orm/dao.ftl", dao);

            // 生成Mapper
            writeFile(project, codeGenContext.getMapperDir(), dao.getModel().getSimpleName() + "Mapper.xml", "domain/orm/mapper.ftl", dao);

            //生成Service
            Service service = null;
            if (codeGenContext.getGenService() || codeGenContext.getGenController()) {
                service = new Service(table.getComment(), codeGenContext.getServicePackage() + CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, table.getName()) + "Service", dao);
                writeFile(project, codeGenContext.getServicePackage(), service.getSimpleName() + ".java", "domain/orm/service.ftl", service);
            }

            //生成Controller
            if (codeGenContext.getGenController()) {
                Controller controller = new Controller(table.getComment(), codeGenContext.getControllerPackage() + CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, table.getName()) + "Controller", service);
                writeFile(project, codeGenContext.getControllerPackage(), controller.getSimpleName() + ".java", "domain/orm/controller.ftl", controller);
            }
        }
    }
}
