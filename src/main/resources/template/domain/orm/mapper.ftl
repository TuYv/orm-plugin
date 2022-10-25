<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<mapper namespace="${package}.${simpleName}">

    <resultMap id="${model.varName}Map" type="${model.package}.${model.simpleName}">
        <#list model.fields as field>
        <#if field.id>
        <id property="${field.name}" column="${field.columnName}"/>
        <#else>
        <result property="${field.name}" column="${field.columnName}"/>
        </#if>
        </#list>
    </resultMap>


    <sql id="Base_Column_List">
        <#list model.fields as field><#if field_index!=0>, ${field.columnName}<#else>${field.columnName}</#if></#list>
    </sql>

    <!--通过ID查询单个${model.comment}-->
    <select id="findById" resultMap="${model.varName}Map">
        SELECT <include refid="Base_Column_List"/> FROM ${model.tableName}
        WHERE <#list model.fields as field><#if field.id>${field.columnName}</#if></#list>=<#noparse>#{id}</#noparse>
    </select>

    <!--新增${model.comment}-->
    <insert id="insert">
        INSERT INTO ${model.tableName}(<#list model.fields as field><#if field_index!=0>,</#if>${field.columnName}</#list>)
        VALUES (<#list model.fields as field><#if field_index!=0>,</#if><#noparse>#{</#noparse>${field.name}}</#list>)
    </insert>

    <!--新增-->
    <insert id="insertSelective" parameterType="${model.package}.${model.simpleName}">
        insert into ${model.tableName}
        <trim prefix="(" suffix=")" suffixOverrides="," >
            <#list model.fields as field>
                <#if field_index!=0>
            <if test="${field.name} != null">
                ${field.columnName},
            </if>
                </#if>
            </#list>
        </trim>
        <trim prefix="values (" suffix=")" suffixOverrides="," >
            <#list model.fields as field>
                <#if field_index!=0>
            <if test="${field.name} != null">
                <#noparse>#</#noparse>{${field.name}},
            </if>
                </#if>
            </#list>
        </trim>
    </insert>

    <!--修改${model.comment}-->
    <update id="update">
        UPDATE ${model.tableName} SET <#list model.fields as field><#if !field.id><#if model.fields[0].id&&field_index!=1>,</#if>${field.columnName}=<#noparse>#{</#noparse>${field.name}}</#if></#list>
        WHERE <#list model.fields as field><#if field.id>${field.columnName}=<#noparse>#{</#noparse>${field.name}}</#if></#list>
    </update>

    <!--修改-->
    <update id="updateSelective" parameterType="${model.package}.${model.simpleName}">
        UPDATE ${model.tableName} SET
        <#list model.fields as field>
            <#if !field.id>
            <if test="${field.name} != null">
                <#if model.fields[0].id&&field_index!=1>,</#if> ${field.columnName}=<#noparse>#{</#noparse>${field.name}}
            </if>
            </#if>
        </#list>
        WHERE
        <#list model.fields as field>
            <#if field.id>
                ${field.columnName}=<#noparse>#{</#noparse>${field.name}}
            </#if>
        </#list>
    </update>

    <!--通过ID删除单个${model.comment}-->
    <delete id="deleteById">
        DELETE FROM ${model.tableName}
        WHERE <#list model.fields as field><#if field.id>${field.columnName}</#if></#list>=<#noparse>#{id}</#noparse>
    </delete>

    <!--批量新增-->
    <insert id="batchInsert" keyColumn="id" keyProperty="id" parameterType="map" useGeneratedKeys="true">
        INSERT INTO ${model.tableName}
        (<#list model.fields as field><#if field_index!=0>,</#if>${field.columnName}</#list>)
        values
        <foreach collection="list" item="item" separator=",">
            (<#list model.fields as field><#if field_index!=0>,</#if><#noparse>#{item.</#noparse>${field.name}}</#list>)
        </foreach>
    </insert>

    <!--批量更新-->
    <update id="batchUpdate" parameterType="list">
        UPDATE ${model.tableName}
        <trim prefix="set" suffixOverrides=",">
            <#list model.fields as field>
                <#if !field.id>
                    <trim prefix="${field.columnName}=case" suffix="end,">
                        <foreach collection="list" item="item" index="index">
                            <if test="item.${field.name} != null">
                                when id=<#noparse>#{item.id} then #{</#noparse>item.${field.name}}
                            </if>
                        </foreach>
                    </trim>
                </#if>
            </#list>
        </trim>
        where
        <foreach collection="list" separator="or" item="item" index="index" >
            id=<#noparse>#{item.id}</#noparse>
        </foreach>
    </update>

</mapper>