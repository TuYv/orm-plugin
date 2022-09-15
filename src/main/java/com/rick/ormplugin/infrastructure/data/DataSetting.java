package com.rick.ormplugin.infrastructure.data;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.openapi.project.Project;
import com.rick.ormplugin.domain.model.vo.ORMConfigVO;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author Max.Tu
 * @program orm-plugin
 * @description
 * @create 2022-07-17 13:01
 **/
@State(name = "DataSetting", storages = @Storage("plugin.xml"))
public class DataSetting implements PersistentStateComponent<DataState> {

    private DataState state = new DataState();

    public static DataSetting getInstance(Project project) {
        return ServiceManager.getService(project, DataSetting.class);
    }

    @Override
    public @Nullable DataState getState() {
        return this.state;
    }

    @Override
    public void loadState(@NotNull DataState state) {
        this.state = state;
    }

    public ORMConfigVO getORMConfig() {
        return state.getOrmConfigVO();
    }
}
