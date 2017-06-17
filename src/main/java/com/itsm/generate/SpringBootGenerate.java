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
public class SpringBootGenerate extends AbstractGenerate{
    public String getOutPath() {
        return "/work/001_code/github/java/fiona-pet/account-springboot/";
    }

    @Override
    public String getUIOutPath() {
        return "/work/001_code/svn/joint/trunk/smart/smart-web/src/";
    }

    public String getPackagePath() {
        return "/cn/fiona/pet/";
    }

    public String getPackage() {
        return "cn.fiona.pet.";
    }

    public String getArtifactId() {
        return "account-";
    }

    public Map<String, String> tableAndDescs() {
        Map<String, String> tableAndDescs = new HashMap<String, String>();
        tableAndDescs.put("t_menu","菜单信息");
        tableAndDescs.put("t_enterprise","企业信息");
        tableAndDescs.put("t_organize","组织信息");
        tableAndDescs.put("t_role","角色信息");
        tableAndDescs.put("t_role_menu","角色和菜单关系信息");
        tableAndDescs.put("t_user_role","用户和角色关系信息");
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
