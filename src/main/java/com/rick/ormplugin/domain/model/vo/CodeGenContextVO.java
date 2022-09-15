package com.rick.ormplugin.domain.model.vo;

import com.rick.ormplugin.infrastructure.po.Table;
import java.util.List;

/**
 * @author Max.Tu
 * @program orm-plugin
 * @description
 * @create 2022-07-17 12:01
 **/
public class CodeGenContextVO {
    private String modelPackage;
    private String daoPackage;
    private String mapperDir;
    private String servicePackage;
    private String controllerPackage;
    private Boolean genController;
    private Boolean genService;

    private List<Table> tables;

    public String getModelPackage() {
        return modelPackage;
    }

    public void setModelPackage(String modelPackage) {
        this.modelPackage = modelPackage;
    }

    public String getDaoPackage() {
        return daoPackage;
    }

    public void setDaoPackage(String daoPackage) {
        this.daoPackage = daoPackage;
    }

    public String getMapperDir() {
        return mapperDir;
    }

    public void setMapperDir(String mapperDir) {
        this.mapperDir = mapperDir;
    }

    public String getServicePackage() {
        return servicePackage;
    }

    public void setServicePackage(String servicePackage) {
        this.servicePackage = servicePackage;
    }

    public String getControllerPackage() {
        return controllerPackage;
    }

    public void setControllerPackage(String controllerPackage) {
        this.controllerPackage = controllerPackage;
    }

    public List<Table> getTables() {
        return tables;
    }

    public void setTables(List<Table> tables) {
        this.tables = tables;
    }

    public Boolean getGenController() {
        return genController;
    }

    public void setGenController(Boolean genController) {
        this.genController = genController;
    }

    public Boolean getGenService() {
        return genService;
    }

    public void setGenService(Boolean genService) {
        this.genService = genService;
    }
}
