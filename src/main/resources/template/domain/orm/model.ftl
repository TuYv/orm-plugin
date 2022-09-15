package ${package};

import lombok.Data;
<#list imports as import>
import ${import};
</#list>

/**
* @author ${author}
* @description ${comment}
* @create ${.now}
*/
@Data
public class ${simpleName} {
<#list fields as field>
    /**
     * ${field.comment}
     */
    private ${field.typeSimpleName} ${field.name};
</#list>
}