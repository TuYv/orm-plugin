package com.rick.ormplugin.ui;

import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.util.NlsContexts.ConfigurableName;
import com.intellij.openapi.vfs.VirtualFile;
import com.rick.ormplugin.domain.model.vo.CodeGenContextVO;
import com.rick.ormplugin.domain.model.vo.ORMConfigVO;
import com.rick.ormplugin.domain.service.IProjectGenerator;
import com.rick.ormplugin.infrastructure.data.DataSetting;
import com.rick.ormplugin.infrastructure.po.Table;
import com.rick.ormplugin.infrastructure.utils.DBHelper;
import com.rick.ormplugin.module.FileChooserComponent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import org.jetbrains.annotations.Nullable;

/**
 * @author Max.Tu
 * @program orm-plugin
 * @description
 * @create 2022-07-16 21:12
 **/
public class ORMSettingsUI implements Configurable {

    private JPanel main;
    private JPanel Settings;
    private JTextField classpath;
    private JTextField host;
    private JButton poButton;
    private JTable table1;
    private JTextField projectName;
    private JTextField poPath;
    private JTextField daoPath;
    private JTextField xmlPath;
    private JTextField port;
    private JTextField database;
    private JTextField user;
    private JPasswordField password;
    private JButton daoButton;
    private JButton xmlButton;
    private JButton selectButton;
    private JButton testButton;
    private JTextField servicePath;
    private JButton serviceButton;
    private JCheckBox serviceCheckBox;
    private JTextField controllerPath;
    private JButton controllerButton;
    private JCheckBox controllerCheckBox;

    private ORMConfigVO config;
    private Project project;
    private IProjectGenerator projectGenerator;

    public ORMSettingsUI(Project project, IProjectGenerator projectGenerator) {
        this.project = project;
        this.projectGenerator = projectGenerator;
        config = DataSetting.getInstance(null != project ? project : ProjectManager.getInstance().getDefaultProject()).getORMConfig();

        //清理历史填过的表名
        config.getTableNames().clear();

        this.projectName.setText(project.getName());
        this.classpath.setText(project.getBasePath());

        this.database.setText(config.getDatabase());
        this.host.setText(config.getHost());
        this.port.setText(config.getPort());
        this.poPath.setText(config.getPoPath());
        this.daoPath.setText(config.getDaoPath());
        this.xmlPath.setText(config.getXmlPath());
        this.servicePath.setText(config.getServicePath());
        this.controllerPath.setText(config.getControllerPath());
        this.user.setText(config.getUser());
        this.password.setText(config.getPassword());
        this.serviceCheckBox.setSelected(config.getGenService());
        this.controllerCheckBox.setSelected(config.getGenController());

        if (Objects.nonNull(config.getHost()) && Objects.nonNull(config.getPort()) && Objects.nonNull(config.getUser()) && Objects.nonNull(config.getPassword())) {
            try {
                testConnect();
                selectDB();
            } catch (Exception e) {
            }
        }

        // 选择CONTROLLER生成目录
        this.controllerButton.addActionListener(e -> {
            FileChooserComponent component = FileChooserComponent.getInstance(project);
            VirtualFile baseDir = project.getBaseDir();
            VirtualFile virtualFile = component.showFolderSelectionDialog("选择CONTROLLER生成目录", baseDir, baseDir);
            if (null != virtualFile) {
                ORMSettingsUI.this.controllerPath.setText(virtualFile.getPath());
            }
        });

        // 选择SERVICE生成目录
        this.serviceButton.addActionListener(e -> {
            FileChooserComponent component = FileChooserComponent.getInstance(project);
            VirtualFile baseDir = project.getBaseDir();
            VirtualFile virtualFile = component.showFolderSelectionDialog("选择SERVICE生成目录", baseDir, baseDir);
            if (null != virtualFile) {
                ORMSettingsUI.this.servicePath.setText(virtualFile.getPath());
            }
        });

        // 选择PO生成目录
        this.poButton.addActionListener(e -> {
            FileChooserComponent component = FileChooserComponent.getInstance(project);
            VirtualFile baseDir = project.getBaseDir();
            VirtualFile virtualFile = component.showFolderSelectionDialog("选择PO生成目录", baseDir, baseDir);
            if (null != virtualFile) {
                ORMSettingsUI.this.poPath.setText(virtualFile.getPath());
            }
        });

        // 选择DAO生成目录
        this.daoButton.addActionListener(e -> {
            FileChooserComponent component = FileChooserComponent.getInstance(project);
            VirtualFile baseDir = project.getBaseDir();
            VirtualFile virtualFile = component.showFolderSelectionDialog("选择DAO生成目录", baseDir, baseDir);
            if (null != virtualFile) {
                ORMSettingsUI.this.daoPath.setText(virtualFile.getPath());
            }
        });

        // 选择XMl生成目录
        this.xmlButton.addActionListener(e -> {
            FileChooserComponent component = FileChooserComponent.getInstance(project);
            VirtualFile baseDir = project.getBaseDir();
            VirtualFile virtualFile = component.showFolderSelectionDialog("选择XML生成目录", baseDir, baseDir);
            if (null != virtualFile) {
                ORMSettingsUI.this.xmlPath.setText(virtualFile.getPath());
            }
        });

        // 查询数据库表列表
        this.selectButton.addActionListener(e -> {
            selectDB();
        });

        // 给表添加事件
        this.table1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (1 == e.getClickCount()) {
                    int rowIdx = table1.rowAtPoint(e.getPoint());
                    Boolean flag = (Boolean) table1.getValueAt(rowIdx, 0);
                    Set<String> tableNames = ORMSettingsUI.this.config.getTableNames();
                    if (null != flag && flag) {
                        tableNames.add(table1.getValueAt(rowIdx, 1).toString());
                    } else {
                        tableNames.remove(table1.getValueAt(rowIdx, 1).toString());
                    }
                }
            }
        });

        // 测试数据库链接
        this.testButton.addActionListener(e -> {
            try {
                String mysqlVersion = testConnect();
                Messages.showInfoMessage(project, "Connection successful! \r\nMySQL version : " + mysqlVersion, "OK");
            } catch (Exception exception) {
                Messages.showWarningDialog(project, "数据库连接错误,请检查配置.", "Warning");
            }
        });

        //controller点击事件
        this.controllerCheckBox.addActionListener(e -> {
            if (this.controllerCheckBox.isSelected()) {
                this.serviceCheckBox.setSelected(true);
            }
        });
        this.serviceCheckBox.addActionListener(e -> {
            if (this.controllerCheckBox.isSelected()) {
                this.serviceCheckBox.setSelected(true);
            }
        });
    }

    @Override
    public @ConfigurableName String getDisplayName() {
        return "Simple Generate";
    }

    @Override
    public @Nullable JComponent createComponent() {
        return main;
    }

    @Override
    public boolean isModified() {
        return true;
    }

    @Override
    public void apply() throws ConfigurationException {
        // 获取配置
        config.setUser(this.user.getText());
        config.setPassword(new String(this.password.getPassword()));
        config.setProjectName(this.projectName.getText());
        config.setClasspath(this.classpath.getText());
        config.setDatabase(this.database.getText());
        config.setHost(this.host.getText());
        config.setPort(this.port.getText() != null ? this.port.getText() : "3306");
        config.setPoPath(this.poPath.getText());
        config.setDaoPath(this.daoPath.getText());
        config.setXmlPath(this.xmlPath.getText());
        config.setServicePath(this.servicePath.getText());
        config.setControllerPath(this.controllerPath.getText());
        config.setGenService(this.serviceCheckBox.isSelected());
        config.setGenController(this.controllerCheckBox.isSelected());

        // 链接DB
        DBHelper dbHelper = new DBHelper(config.getHost(), Integer.parseInt(config.getPort()), config.getUser(), config.getPassword(), config.getDatabase());

        // 组装代码生产上下文
        CodeGenContextVO codeGenContext = new CodeGenContextVO();
        codeGenContext.setModelPackage(config.getPoPath() + "/entity/");
        codeGenContext.setDaoPackage(config.getDaoPath() + "/mapper/");
        codeGenContext.setMapperDir(config.getXmlPath() + "/mapper/");
        codeGenContext.setServicePackage(config.getServicePath() + "/service/");
        codeGenContext.setControllerPackage(config.getControllerPath() + "/controller/");
        codeGenContext.setGenService(config.getGenService());
        codeGenContext.setGenController(config.getGenController());
        List<Table> tables = new ArrayList<>();
        Set<String> tableNames = config.getTableNames();
        for (String tableName : tableNames) {
            tables.add(dbHelper.getTable(tableName));
        }
        codeGenContext.setTables(tables);

        // 生成代码
        projectGenerator.generation(project, codeGenContext);

        Messages.showInfoMessage(project, "生成成功！", "OK");

    }

    private String testConnect() throws Exception {
        DBHelper dbHelper = new DBHelper(this.host.getText(), Integer.parseInt(this.port.getText()), this.user.getText(), this.password.getText(), this.database.getText());
        return dbHelper.testDatabase();
    }

    private void selectDB() {
        try {
            DBHelper dbHelper = new DBHelper(this.host.getText(), Integer.parseInt(this.port.getText()), this.user.getText(), this.password.getText(), this.database.getText());
            List<String> tableList = dbHelper.getAllTableName(this.database.getText());

            String[] title = {"", "表名"};
            Object[][] data = new Object[tableList.size()][2];
            for (int i = 0; i < tableList.size(); i++) {
                data[i][1] = tableList.get(i);
            }

            table1.setModel(new DefaultTableModel(data, title));
            TableColumn tc = table1.getColumnModel().getColumn(0);
            tc.setCellEditor(new DefaultCellEditor(new JCheckBox()));
            tc.setCellEditor(table1.getDefaultEditor(Boolean.class));
            tc.setCellRenderer(table1.getDefaultRenderer(Boolean.class));
            tc.setMaxWidth(100);
        } catch (Exception exception) {
            Messages.showWarningDialog(project, "数据库查询异常,请检查配置.", "Warning");
        }
    }
}
