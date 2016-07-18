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

    protected Field toField(ResultSet resultSet, String tableName) {
        Field field = new Field();
        Map<String, String> fieldAndDescs = fieldAndDesc(tableName);
        try {
            field.setName(resultSet.getString("Field"));
            field.setDesc(fieldAndDescs.get(resultSet.getString("Field")));
            field.setType(toJavaType(resultSet.getString("Type")));
            field.setIsNull(resultSet.getString("Null"));
        } catch (SQLException e) {
            LOGGER.warn("to field error!", e);
        }
        return field;
    }

    public String[] getFilterField() {
        return new String[]{"uuid","createUserId","createDate", "updateUserId", "updateDate","status"};
    }

    private Map<String, String> fieldAndDesc(String tableName){
        Map<String, String> fieldAndDescs = null;
        if (TABLE_SCHEMA_SQL_MAP.containsKey(tableName)){
            fieldAndDescs = TABLE_SCHEMA_SQL_MAP.get(tableName);
        }else {
            fieldAndDescs = new HashMap<String, String>();

            List<String> tableSchemas = tableSchema(tableName);
            LOGGER.info("{}:tableSchemas->{}",tableName, tableSchemas);

            for (String line : tableSchemas) {
                if (line.indexOf("--") > -1) {
                    String[] fieldAndDesc = line.split("--");
                    if (fieldAndDesc.length < 2){
                        break;
                    }
                    String fieldName = StringUtils.trimToEmpty(fieldAndDesc[0]).split(" ")[0];
                    if (fieldName.indexOf("`")>-1){
                        fieldName = fieldName.substring(1, fieldName.length()-1);
                    }
                    fieldAndDescs.put(fieldName, StringUtils.trimToEmpty(fieldAndDesc[1]));
                }
            }

            TABLE_SCHEMA_SQL_MAP.put(tableName, fieldAndDescs);

            LOGGER.info("{}:{}",tableName, fieldAndDescs);
        }

        return fieldAndDescs;
    }

    private List<String> tableSchema(String tableName) {
        List<String> schemas = new ArrayList<String>();
        if (SCHEMA_SQL_LIST.size() == 0){
            try {
                SCHEMA_SQL_LIST.addAll(FileUtils.readLines(new File(SCHEMA_SQL)));
            } catch (IOException e) {
                LOGGER.warn("read schema sql error!", e);
            }
        }

        int start = SCHEMA_SQL_LIST.indexOf("DROP TABLE IF EXISTS `"+tableName+"`;");
        for (int i = start; i < SCHEMA_SQL_LIST.size();i++){
            String line = SCHEMA_SQL_LIST.get(i);
            if (line.indexOf(") ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;")>-1){
                break;
            }

            schemas.add(line);
        }

        return schemas;
    }
}

