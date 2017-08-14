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
public class SpringBootBusinessGenerate extends AbstractGenerate{
    public String getOutPath() {
        return "/work/001_code/github/java/fiona-pet/business-springboot/";
    }

    @Override
    public String getUIOutPath() {
        return "/work/001_code/svn/joint/trunk/smart/smart-web/src/";
    }

    public String getPackagePath() {
        return "/com/fionapet/business/";
    }

    public String getPackage() {
        return "com.fionapet.business.";
    }

    public String getArtifactId() {
        return "business-";
    }

    public Map<String, String> tableAndDescs() {
        Map<String, String> tableAndDescs = new HashMap<String, String>();
        tableAndDescs.put("t_project", "宠物");
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
