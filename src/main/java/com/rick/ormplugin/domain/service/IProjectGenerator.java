package com.rick.ormplugin.domain.service;

import com.intellij.openapi.project.Project;
import com.rick.ormplugin.domain.model.vo.CodeGenContextVO;

public interface IProjectGenerator {

    void generation(Project project, CodeGenContextVO codeGenContext);
}
