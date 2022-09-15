package ${package};

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
<#list imports as import>
import ${import};
</#list>

/**
* @author ${author}
* @description ${comment}
* @create ${.now}
*/
@Service
public class ${simpleName} {

    @Resource
    private ${dao.simpleName} ${dao.varName};

<#list dao.model.fields as field>
    <#if field.id>
        /**
        * 通过ID查询单个${dao.model.comment}
        *
        * @param id ID
        * @return {@link ${dao.model.simpleName}}
        */
        ${dao.model.simpleName} findById(${field.typeSimpleName} id) {
            return ${dao.varName}.findById(id);
        }

        /**
        * 新增${dao.model.comment}
        *
        * @param ${dao.model.varName} ${dao.model.comment}
        */
        void insert(${dao.model.simpleName} ${dao.model.varName}) {
        ${dao.varName}.insertSelective(${dao.model.varName});
        }

        /**
        * 修改${dao.model.comment}
        *
        * @param ${dao.model.varName} ${dao.model.comment}
        */
        void update(${dao.model.simpleName} ${dao.model.varName}) {
            ${dao.varName}.updateSelective(${dao.model.varName});
        }

        /**
        * 通过ID删除单个${dao.model.comment}
        *
        * @param id ID
        */
        void deleteById(${field.typeSimpleName} id){
            ${dao.varName}.deleteById(id);
        }
    </#if>
</#list>

}