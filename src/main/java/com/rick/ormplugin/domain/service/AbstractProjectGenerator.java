package com.rick.ormplugin.domain.service;

import com.intellij.openapi.application.Application;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.VirtualFileManager;
import com.rick.ormplugin.Config;
import com.rick.ormplugin.domain.model.vo.CodeGenContextVO;
import com.rick.ormplugin.ui.ReWriteDialog;
import com.twelvemonkeys.lang.StringUtil;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;

/**
 * @author Max.Tu
 * @program orm-plugin
 * @description
 * @create 2022-07-17 12:07
 **/
public abstract class AbstractProjectGenerator extends GeneratorConfig implements IProjectGenerator{

    @Override
    public void generation(Project project, CodeGenContextVO codeGenContext) {
        generateORM(project, codeGenContext);
    }

    protected abstract void generateORM(Project project, CodeGenContextVO codeGenContext);

    public void writeFile(Project project, String packageName, String name, String ftl, Object dataModel) {
        if (isNeedReWrite(project, packageName, name)) {
            VirtualFile virtualFile = null;
            try {
                VirtualFileManager.getInstance().syncRefresh();
                virtualFile = createPackageDir(packageName).createChildData(project, name);
                StringWriter stringWriter = new StringWriter();
                Template template = super.getTemplate(ftl);
                template.process(dataModel, stringWriter);
                Application applicationManager = ApplicationManager.getApplication();
                VirtualFile finalVirtualFile = virtualFile;
                applicationManager.runWriteAction(() -> {
                    try {
                        finalVirtualFile.setBinaryContent(stringWriter.toString().getBytes(StandardCharsets.UTF_8));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            } catch (IOException | TemplateException e) {
                e.printStackTrace();
            }
        }
    }

    public Boolean isNeedReWrite(Project project, String packageName, String name) {
        if (isExist(packageName, name)) {
            Config.reWriteFile = getPath(packageName, name);
            if (new ReWriteDialog(project).showAndGet()) {
                return removeFile(packageName, name);
            }
        } else {
            return true;
        }
        return false;
    }

    private static VirtualFile createPackageDir(String packageName) {
        String path = FileUtil.toSystemDependentName(StringUtil.replace(packageName, ".", "/"));
        new File(path).mkdirs();
        return LocalFileSystem.getInstance().refreshAndFindFileByPath(path);
    }

    private static Boolean isExist(String packageName, String name) {
        File file = new File(getPath(packageName, name));
        return file.exists();
    }

    private static boolean removeFile(String packageName, String name) {
        File file = new File(getPath(packageName, name));
        return file.delete();
    }

    private static String getPath(String packageName, String name) {
        return FileUtil.toSystemDependentName(StringUtil.replace(packageName, ".", "/")) + "/" + name;
    }
}
