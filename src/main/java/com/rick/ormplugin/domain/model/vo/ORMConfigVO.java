package com.rick.ormplugin.domain.model.vo;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author Max.Tu
 * @program orm-plugin
 * @description
 * @create 2022-07-17 13:03
 **/
public class ORMConfigVO {

    private String url;

    private String user;

    private String password;

    private String projectName;

    private String classpath;

    private Integer transactionType;

    private String transactionName;

    private Integer isColumnNameDelimited;

    private Set<String> tableNames = new LinkedHashSet<>();

    private String generatorType;

    private String poPath;

    private String daoPath;

    private String servicePath;

    private String xmlPath;

    private String controllerPath;

    private String host;

    private String port = "3306";

    private String database;

    private Boolean isGenService = false;

    private Boolean isGenController = false;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getClasspath() {
        return classpath;
    }

    public void setClasspath(String classpath) {
        this.classpath = classpath;
    }

    public Integer getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(Integer transactionType) {
        this.transactionType = transactionType;
    }

    public String getTransactionName() {
        return transactionName;
    }

    public void setTransactionName(String transactionName) {
        this.transactionName = transactionName;
    }

    public Integer getIsColumnNameDelimited() {
        return isColumnNameDelimited;
    }

    public void setIsColumnNameDelimited(Integer isColumnNameDelimited) {
        this.isColumnNameDelimited = isColumnNameDelimited;
    }

    public Set<String> getTableNames() {
        return tableNames;
    }

    public void setTableNames(Set<String> tableNames) {
        this.tableNames = tableNames;
    }

    public String getGeneratorType() {
        return generatorType;
    }

    public void setGeneratorType(String generatorType) {
        this.generatorType = generatorType;
    }

    public String getPoPath() {
        return poPath;
    }

    public void setPoPath(String poPath) {
        this.poPath = poPath;
    }

    public String getDaoPath() {
        return daoPath;
    }

    public void setDaoPath(String daoPath) {
        this.daoPath = daoPath;
    }

    public String getServicePath() {
        return servicePath;
    }

    public void setServicePath(String servicePath) {
        this.servicePath = servicePath;
    }

    public String getXmlPath() {
        return xmlPath;
    }

    public void setXmlPath(String xmlPath) {
        this.xmlPath = xmlPath;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getControllerPath() {
        return controllerPath;
    }

    public void setControllerPath(String controllerPath) {
        this.controllerPath = controllerPath;
    }

    public Boolean getGenService() {
        return isGenService;
    }

    public void setGenService(Boolean genService) {
        isGenService = genService;
    }

    public Boolean getGenController() {
        return isGenController;
    }

    public void setGenController(Boolean genController) {
        isGenController = genController;
    }
}
