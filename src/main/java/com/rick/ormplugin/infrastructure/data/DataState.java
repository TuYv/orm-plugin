package com.rick.ormplugin.infrastructure.data;

import com.rick.ormplugin.domain.model.vo.ORMConfigVO;

/**
 * @author Max.Tu
 * @program orm-plugin
 * @description
 * @create 2022-07-17 13:01
 **/
public class DataState {
    private ORMConfigVO ormConfigVO = new ORMConfigVO();

    public ORMConfigVO getOrmConfigVO() {
        return ormConfigVO;
    }

    public void setOrmConfigVO(ORMConfigVO ormConfigVO) {
        this.ormConfigVO = ormConfigVO;
    }
}
