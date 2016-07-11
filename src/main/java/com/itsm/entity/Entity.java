package com.itsm.entity;

import com.itsm.Main;
import com.itsm.util.Utils;

import java.util.List;

/**
 * Created by itsm on 16/7/2.
 */
public class Entity {
    public static final String OUT_PATH = "/work/001_code/github/java/itsm-platform/itsm-account/";
    public static final String OUT_API_PATH = OUT_PATH + Main.ARTIFACT_ID + "api/src/main/java" + Main.PACKAGE_PATH;
    public static final String OUT_REST_PATH = OUT_PATH + Main.ARTIFACT_ID + "rest-provider/src/main/java" + Main.PACKAGE_PATH;
    public static final String OUT_SERVICE_PATH = OUT_PATH + Main.ARTIFACT_ID + "service-provider/src/main/java" + Main.PACKAGE_PATH;
    public static final String OUT_TEST_DAO_PATH = OUT_PATH + Main.ARTIFACT_ID + "service-provider/src/test/java" + Main.PACKAGE_PATH;
    public static final String OUT_CONSUMER_PATH = OUT_PATH + Main.ARTIFACT_ID + "consumer/src/main/java" + Main.PACKAGE_PATH;
    public static final String OUT_REST_CLIENT_PATH = OUT_PATH + Main.ARTIFACT_ID + "consumer/src/test/java" + Main.PACKAGE_PATH;

    public static final String[] FILTER_FIELD = new String[]{"id","status"};


    private String entityName;
    private String entityClassName;
    private String tableName;
    private List<Field> fields;

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public String getEntityClassName() {
        return Utils.underlineToCamel(this.getTableName()).substring(1);
    }

    public String getFieldClassName() {
        return Utils.firstLetterToLower(getEntityClassName());
    }

    public String getPath() {
        return getEntityClassName().toLowerCase() + "s";
    }


    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public List<Field> getFields() {
        return fields;
    }

    public void setFields(List<Field> fields) {
        this.fields = Utils.filterField(fields);
    }
}
