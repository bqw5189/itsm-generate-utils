package com.itsm.entity;

import com.itsm.util.Utils;

import java.util.List;

/**
 * Created by itsm on 16/7/2.
 */
public class Entity {
    private String entityName;
    private String entityClassName;
    private String tableName;
    private List<Field> fields;

    private boolean hasView = true;

    public boolean isHasView() {
        return hasView;
    }

    public void setHasView(boolean hasView) {
        this.hasView = hasView;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public String getEntityClassName() {
        return Utils.firstLetterToUpper(Utils.underlineToCamel(this.getTableName()).substring(1));
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
        this.fields = fields;
    }
}
