package com.itsm.jdbc;

import com.itsm.entity.Field;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by itsm on 16/7/2.
 */
public class MysqlJdbcUtils extends JdbcUtils{
    //数据库用户名
    private static final String USERNAME = "test";
    //数据库密码
    private static final String PASSWORD = "123456";
    //驱动信息
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    //数据库地址
    private static final String URL = "jdbc:mysql://localhost:3306/fiona_pet_business";

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

    protected String getDescSql(String tableName) {
        return " desc "+tableName +"";
    }

    protected Field toField(ResultSet resultSet) {
        Field field = new Field();
        try {
            field.setName(resultSet.getString("Field"));
            field.setType(resultSet.getString("Type"));
            field.setIsNull(resultSet.getString("Null"));
        } catch (SQLException e) {
            LOGGER.warn("to field error!", e);
        }
        return field;
    }

    public String[] getFilterField() {
        return new String[]{"uuid","createUserId","createDate", "updateUserId", "updateDate","status"};
    }
}

