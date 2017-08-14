package com.itsm.generate;

import com.itsm.jdbc.JdbcUtils;
import com.itsm.jdbc.MysqlJdbcUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tom on 16/7/13.
 */
public class SpringBootWorkGenerate extends AbstractGenerate{
    public String getOutPath() {
        return "/work/001_code/github/java/ruijie/service/";
    }

    @Override
    public String getUIOutPath() {
        return "/work/001_code/svn/joint/trunk/smart/smart-web/src/";
    }

    public String getPackagePath() {
        return "/com/ruijie/service/";
    }

    public String getPackage() {
        return "com.ruijie.service.";
    }

    public String getArtifactId() {
        return "service-";
    }

    public Map<String, String> tableAndDescs() {
        Map<String, String> tableAndDescs = new HashMap<String, String>();
        tableAndDescs.put("t_project", "项目信息");
        tableAndDescs.put("t_working", "服务人员");
        tableAndDescs.put("t_working_hours", "工时记录");
        return tableAndDescs;
    }

    @Override
    public boolean hasView(String tableName) {
        List<String> views = new ArrayList<String>();
        return views.contains(tableName);
    }

    public JdbcUtils getJdbcUtils() {
        return new MysqlJdbcUtils();
    }
}
