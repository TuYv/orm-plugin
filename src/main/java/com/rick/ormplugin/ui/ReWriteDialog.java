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
        setTitle("文件已存在");
        init();
    }

    @Override
    protected @Nullable JComponent createCenterPanel() {
        JPanel dialogPanel = new JPanel(new BorderLayout());

        JLabel label = new JLabel("文件" + Config.reWriteFile + "已存在，是否覆盖？");
        label.setPreferredSize(new Dimension(100, 100));
        dialogPanel.add(label, BorderLayout.CENTER);

        return dialogPanel;
    }
}
