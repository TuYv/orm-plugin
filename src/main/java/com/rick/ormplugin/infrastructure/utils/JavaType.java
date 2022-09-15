package com.rick.ormplugin.infrastructure.utils;

import java.math.BigDecimal;
import java.sql.JDBCType;
import java.util.Date;
import java.util.LinkedHashMap;

public class JavaType {

    private static LinkedHashMap<JDBCType, Class> map = new LinkedHashMap();

    static {
        //字符串类型
        map.put(JDBCType.VARCHAR, String.class);
        map.put(JDBCType.LONGVARCHAR, String.class);
        map.put(JDBCType.CHAR, String.class);
        //整数类型
        map.put(JDBCType.INTEGER, Integer.class);
        map.put(JDBCType.BIGINT, Long.class);
        map.put(JDBCType.SMALLINT, Integer.class);
        map.put(JDBCType.TINYINT, Integer.class);
        //浮点类型
        map.put(JDBCType.FLOAT, Float.class);
        map.put(JDBCType.DOUBLE, Double.class);
        map.put(JDBCType.DECIMAL, BigDecimal.class);
        //其他类型
        map.put(JDBCType.BOOLEAN, Boolean.class);
        map.put(JDBCType.DATE, Date.class);
        map.put(JDBCType.TIME, Date.class);
        map.put(JDBCType.TIMESTAMP, Date.class);
        map.put(JDBCType.BIT, boolean.class);
    }

    public static Class convertType(int sqlType) {
        return map.getOrDefault(JDBCType.valueOf(sqlType), Object.class);
    }

}
