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
        return "/work/001_code/github/java/uics/uics-grab/";
    }

    public String getPackagePath() {
        return "/com/uics/grab/";
    }

    public String getPackage() {
        return "com.uics.grab.";
    }

    public String getArtifactId() {
        return "uics-grab-";
    }

    public Map<String, String> tableAndDescs() {
        Map<String, String> tableAndDescs = new HashMap<String, String>();
        tableAndDescs.put("t_vrv_config", "vrv配置信息");
        tableAndDescs.put("t_vrv_target", "vrv监控指标统计信息");
        tableAndDescs.put("t_h3c_realtime_fault", "h3c告警信息");
        tableAndDescs.put("t_idcs_todo", "idcs代办");
        tableAndDescs.put("t_idcs_notification", "idcs通知");
        return tableAndDescs;
    }

    public JdbcUtils getJdbcUtils() {
        return new PostgreJdbcUtils();
    }
}
