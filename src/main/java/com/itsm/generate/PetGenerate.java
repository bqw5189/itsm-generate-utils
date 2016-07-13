package com.itsm.generate;

import com.itsm.jdbc.JdbcUtils;
import com.itsm.jdbc.MysqlJdbcUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by tom on 16/7/13.
 */
public class PetGenerate extends AbstractGenerate{
    public String getOutPath() {
        return "/work/001_code/github/java/pet-hospital/fionapet-business/";
    }

    public String getPackagePath() {
        return "/com/fionapet/business/";
    }

    public String getPackage() {
        return "com.fionapet.business.";
    }

    public String getArtifactId() {
        return "fionapet-business-";
    }

    public Map<String, String> tableAndDescs() {
        Map<String, String> tableAndDescs = new HashMap<String, String>();
        tableAndDescs.put("t_app_config", "应用配置");
        return tableAndDescs;
    }

    public JdbcUtils getJdbcUtils() {
        return new MysqlJdbcUtils();
    }
}
