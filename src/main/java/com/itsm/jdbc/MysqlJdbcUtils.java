package com.itsm.jdbc;

import com.itsm.entity.Field;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by itsm on 16/7/2.
 */
public class MysqlJdbcUtils extends JdbcUtils{
    private static final String SCHEMA_SQL = "/work/001_code/github/java/pet-hospital/fionapet-business/fionapet-business-service-provider/src/main/resources/sql/mysql/schema.sql";
    private static final List<String> SCHEMA_SQL_LIST = new ArrayList<String>();
    private static final Map<String, Map<String, String>> TABLE_SCHEMA_SQL_MAP = new HashMap<String, Map<String, String>>();
    //数据库用户名
    private static final String USERNAME = "test";
    //数据库密码
    private static final String PASSWORD = "123456";
    //驱动信息
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    //数据库地址
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/service";

    private static final Logger LOGGER = LoggerFactory.getLogger(MysqlJdbcUtils.class);


    public String getDriver() {
        return DRIVER;
    }

    public String getUserName() {
        return USERNAME;
    }

    public String getPassword() {
        return PASSWORD;
    }

    public String getUrl() {
        return URL;
    }

    public static final String DESC_SQL = "select column_name AS `列名`,\n" +
            "    data_type   AS `数据类型`,\n" +
            "    character_maximum_length  AS `字符长度`,\n" +
            "    numeric_precision AS `数字长度`,\n" +
            "    numeric_scale AS `小数位数`,\n" +
            "    is_nullable AS `是否允许非空`,\n" +
            "    extra AS `是否自增`,\n" +
            "    column_default  AS  `默认值`,\n" +
            "    column_comment  AS  `备注`\n" +
            "    from information_schema.columns where table_name='%s'  and table_schema='service'";

    protected String getDescSql(String tableName) {
        return String.format(DESC_SQL, tableName);
    }

    protected Field toField(ResultSet resultSet, String tableName) {
        Field field = new Field();
        try {
            field.setColumnName(resultSet.getString("列名"));
            field.setDesc(resultSet.getString("备注"));
            field.setType(toJavaType(resultSet.getString("数据类型")));
            field.setIsNull(resultSet.getString("是否允许非空"));
        } catch (SQLException e) {
            LOGGER.warn("to field error!", e);
        }
        LOGGER.info("fields:{}", field);

        return field;
    }

    public String[] getFilterField() {
        return new String[]{"id","uuid","createUserId","createDate", "updateUserId", "updateDate","status"};
    }
}

