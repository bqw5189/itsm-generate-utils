package com.itsm.generate;

import com.itsm.jdbc.JdbcUtils;
import com.itsm.jdbc.MysqlJdbcUtils;
import com.itsm.jdbc.PostgreJdbcUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by tom on 16/7/13.
 */
public class ItsmGenerate extends AbstractGenerate{
    public String getOutPath() {
        return "/work/001_code/github/java/uics/uics-asset/";
    }

    public String getPackagePath() {
        return "/com/uics/asset/";
    }

    public String getPackage() {
        return "com.uics.asset.";
    }

    public String getArtifactId() {
        return "uics-asset-";
    }

    public Map<String, String> tableAndDescs() {
        Map<String, String> tableAndDescs = new HashMap<String, String>();
        tableAndDescs.put("t_attribute", "属性信息");
        tableAndDescs.put("t_ci", "配置信息");
        tableAndDescs.put("t_cmdb_tx", "事务信息");
        tableAndDescs.put("t_rfc", "变更信息");
        return tableAndDescs;
    }

    public JdbcUtils getJdbcUtils() {
        return new MysqlJdbcUtils();
    }
}
