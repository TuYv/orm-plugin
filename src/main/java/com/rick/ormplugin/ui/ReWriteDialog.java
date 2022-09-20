package com.rick.ormplugin.ui;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.rick.ormplugin.Config;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.jetbrains.annotations.Nullable;

/**
 * @author Max.Tu
 * @program orm-plugin
 * @description 覆盖重写对话框
 * @create 2022-08-01 21:40
 **/
public class ReWriteDialog extends DialogWrapper {

    public ReWriteDialog(@Nullable Project project) {
        super(project);
        setTitle("file already exists");
        init();
    }

    @Override
    protected @Nullable JComponent createCenterPanel() {
        JPanel dialogPanel = new JPanel(new BorderLayout());

        JLabel label = new JLabel("File: " + Config.reWriteFile + "already exists，is covered？");
        label.setPreferredSize(new Dimension(100, 100));
        dialogPanel.add(label, BorderLayout.CENTER);

        return dialogPanel;
    }
}
