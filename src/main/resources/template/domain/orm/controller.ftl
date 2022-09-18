package ${package};

import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
<#list imports as import>
import ${import};
</#list>

/**
* @author ${author}
* @description ${comment}
* @create ${.now}
*/
@RestController
@RequestMapping("/${model.varName}")
public class ${simpleName} {

    @Resource
    private ${service.simpleName} ${service.varName};

<#list model.fields as field>
    <#if field.id>
        /**
        * 通过ID查询单个${model.comment}
        *
        * @param id ID
        * @return {@link ${model.simpleName}}
        */
        @RequestMapping(value = "/get", method = RequestMethod.GET)
        public ${model.simpleName} findById(@RequestParam("id") ${field.typeSimpleName} id) {
            return ${service.varName}.findById(id);
        }

        /**
        * 新增${model.comment}
        *
        * @param ${model.varName} ${model.comment}
        */
        @RequestMapping(value = "/insert", method = RequestMethod.POST)
        public void insert(@RequestBody ${model.simpleName} ${model.varName}) {
            ${service.varName}.insert(${model.varName});
        }

        /**
        * 修改${model.comment}
        *
        * @param ${model.varName} ${model.comment}
        */
        @RequestMapping(value = "/update", method = RequestMethod.POST)
        public void update(@RequestBody ${model.simpleName} ${model.varName}) {
            ${service.varName}.update(${model.varName});
        }

        /**
        * 通过ID删除单个${model.comment}
        *
        * @param id ID
        */
        @RequestMapping(value = "/update", method = RequestMethod.GET)
        public void deleteById(@RequestParam("id") ${field.typeSimpleName} id){
            ${service.varName}.deleteById(id);
        }
    </#if>
</#list>

}