package com.itsm.entity;

import com.itsm.util.Utils;

/**
 * Created by itsm on 16/7/2.
 */
public class Field {
    private String name;
    private String type;
    private String isNull;
    private String monthName;
    private String desc;

    public String getName() {
        return Utils.underlineToCamel(name);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return Utils.toJavaType(type);
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIsNull() {
        return isNull;
    }

    public void setIsNull(String isNull) {
        this.isNull = isNull;
    }

    public String getMonthName() {

        return Utils.firstLetterToUpper(this.getName());
    }

    public String getDesc() {
        if (this.desc == null ){
            this.desc = this.getName();
        }
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "Field{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", isNull='" + isNull + '\'' +
                ", monthName='" + monthName + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}
