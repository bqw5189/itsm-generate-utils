package com.itsm.jdbc;

import com.itsm.entity.Field;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by itsm on 16/7/2.
 */
public abstract class JdbcUtils {
    public abstract String getDriver();
    public abstract String getUserName();
    public abstract String getPassword();
    public abstract String getUrl();


    public static final char UNDERLINE = '_';

    private Connection connection;

    private PreparedStatement pstmt;

    private ResultSet resultSet;

    public JdbcUtils() {
        // TODO Auto-generated constructor stub
        try {
            Class.forName(getDriver());
            System.out.println("数据库连接成功！");

        } catch (Exception e) {

        }

        this.getConnection();
    }

    /**
     * 获得数据库的连接
     *
     * @return
     */
    public Connection getConnection() {
        try {
            connection = DriverManager.getConnection(getUrl(), getUserName(), getPassword());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public List<Field> desc(String tableName) throws SQLException {
        List<Field> list = new ArrayList<Field>();

        pstmt = connection.prepareStatement(getDescSql(tableName));

        resultSet = pstmt.executeQuery();

        while (resultSet.next()) {
            Field field = toField(resultSet);

            if (!isFilterField(field.getName())) {
                list.add(field);
            }
        }

        return list;
    }

    protected abstract String getDescSql(String tableName);

    protected abstract Field toField(ResultSet resultSet);

    public  String toJavaType(String type) {
        if (StringUtils.indexOf(type,"timestamp") > -1){
            return "Date";
        }else if (StringUtils.indexOf(type,"tinyint") > -1){
            return "Boolean";
        }else if (StringUtils.indexOf(type,"int") > -1){
            return "Integer";
        }else if (StringUtils.indexOf(type,"double") > -1){
            return "double";
        }
        return "String";
    }

    public boolean isFilterField(String name) {
        return ArrayUtils.contains(getFilterField(), name);
    }

    public abstract String[] getFilterField();
}

