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
        return "/work/001_code/github/java/itsm-platform/itsm-account";
    }

    public String getPackagePath() {
        return "/com/itsm/platform/account/";
    }

    public String getPackage() {
        return "com.itsm.platform.account.";
    }

    public String getArtifactId() {
        return "itsm-account-";
    }

    public Map<String, String> tableAndDescs() {
        Map<String, String> tableAndDescs = new HashMap<String, String>();
        tableAndDescs.put("t_um_function", "功能表");
        return tableAndDescs;
    }

    public JdbcUtils getJdbcUtils() {
        return new PostgreJdbcUtils();
    }
}
