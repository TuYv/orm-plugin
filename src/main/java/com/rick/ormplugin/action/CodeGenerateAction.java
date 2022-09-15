package com.rick.ormplugin.action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.options.ShowSettingsUtil;
import com.intellij.openapi.project.Project;
import com.rick.ormplugin.domain.service.IProjectGenerator;
import com.rick.ormplugin.domain.service.impl.ProjectGeneratorImpl;
import com.rick.ormplugin.ui.ORMSettingsUI;
import org.jetbrains.annotations.NotNull;

/**
 * @author Max.Tu
 * @program orm-plugin
 * @description
 * @create 2022-07-16 23:31
 **/
public class CodeGenerateAction extends AnAction {

    private IProjectGenerator projectGenerator = new ProjectGeneratorImpl();

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        Project project = e.getRequiredData(CommonDataKeys.PROJECT);
        ShowSettingsUtil.getInstance().editConfigurable(project, new ORMSettingsUI(project, projectGenerator));
    }
}
