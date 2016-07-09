package com.itsm.jdbc;

import com.itsm.entity.Field;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by itsm on 16/7/2.
 */
public class JdbcUtils {
    //数据库用户名
    private static final String USERNAME = "postgres";
    //数据库密码
    private static final String PASSWORD = "postgres";
    //驱动信息
    private static final String DRIVER = "org.postgresql.Driver";
    //数据库地址
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final char UNDERLINE = '_';

    private Connection connection;

    private PreparedStatement pstmt;

    private ResultSet resultSet;

    public JdbcUtils() {
        // TODO Auto-generated constructor stub
        try {
            Class.forName(DRIVER);
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
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return connection;
    }


    public List<Field> desc(String tableName) throws SQLException {
        List<Field> list = new ArrayList<Field>();

        pstmt = connection.prepareStatement("select * from table_msg('public','"+tableName+"')");

        resultSet = pstmt.executeQuery();

        while (resultSet.next()) {
            Field field = new Field();
            field.setName(resultSet.getString("fields_name"));
            field.setType(resultSet.getString("fields_type"));
            field.setIsNull(resultSet.getString("fields_not_null"));
            field.setDesc(resultSet.getString("fields_comment"));
            list.add(field);
        }

        return list;
    }



//    /**
//     * @param args
//     */
//    public static void main(String[] args) throws SQLException {
//        // TODO Auto-generated method stub
//        JdbcUtils jdbcUtils = new JdbcUtils();
//        jdbcUtils.getConnection();
//
//        String tableName = "t_pet_small_race";
//
//        List<Map<String, String>> descs = jdbcUtils.desc(tableName);
//
//        for (Map<String, String> desc:descs){
//            System.out.println(desc.get("Field") + "-" + Utils.underlineToCamel(desc.get("Field")));
//        }
//    }
}

