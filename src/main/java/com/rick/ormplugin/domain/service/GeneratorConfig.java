package com.rick.ormplugin.domain.service;

import freemarker.template.Configuration;
import freemarker.template.Template;
import java.io.IOException;

/**
 * @author Max.Tu
 * @program orm-plugin
 * @description
 * @create 2022-07-17 12:07
 **/
public class GeneratorConfig {
    public static final String ENCODING = "UTF-8";
    private static  FreemarkerConfiguration freemarker = new FreemarkerConfiguration("/template");

    protected Template getTemplate(String ftl) throws IOException {
        return freemarker.getTemplate(ftl, ENCODING);
    }


    static class FreemarkerConfiguration extends Configuration{

        public FreemarkerConfiguration(String basePackagePath) {
            super(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
            setDefaultEncoding(ENCODING);
            setClassForTemplateLoading(getClass(), basePackagePath);
        }

    }
}
