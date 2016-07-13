package com.itsm.jdbc;

import com.itsm.entity.Field;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

/**
 * Created by itsm on 16/7/2.
 */
public class PostgreJdbcUtils extends JdbcUtils{
    //数据库用户名
    private static final String USERNAME = "postgres";
    //数据库密码
    private static final String PASSWORD = "postgres";
    //驱动信息
    private static final String DRIVER = "org.postgresql.Driver";
    //数据库地址
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";

    private static final Logger LOGGER = LoggerFactory.getLogger(PostgreJdbcUtils.class);


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

    protected String getDescSql(String tableName) {
        return "select * from table_msg('public','"+tableName+"')";
    }

    protected Field toField(ResultSet resultSet) {
        Field field = new Field();
        try {
            field.setName(resultSet.getString("fields_name"));
            field.setType(toJavaType(resultSet.getString("fields_type")));
            field.setIsNull(resultSet.getString("fields_not_null"));
            field.setDesc(resultSet.getString("fields_comment"));
        } catch (SQLException e) {
            LOGGER.warn("to field error!", e);
        }
        return field;
    }

    public String[] getFilterField() {
        return new String[]{"id","status"};
    }
}

