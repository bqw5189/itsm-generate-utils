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
public class WXGenerate extends AbstractGenerate{
    public String getOutPath() {
        return "/work/001_code/svn/smart/";
    }

    @Override
    public String getUIOutPath() {
        return "/work/001_code/github/js/antd-admin/src/";
    }

    public String getPackagePath() {
        return "/com/its/smart/";
    }

    public String getPackage() {
        return "com.its.smart.";
    }

    public String getArtifactId() {
        return "smart-";
    }

    public Map<String, String> tableAndDescs() {
        Map<String, String> tableAndDescs = new HashMap<String, String>();
        tableAndDescs.put("t_wx_user","微信用户信息");
        tableAndDescs.put("t_audit_log","审计日志");
        tableAndDescs.put("t_business","企业信息");
        tableAndDescs.put("t_deparment","部门信息");
        tableAndDescs.put("t_deparment_user_rel","部门用户关系信息");
        tableAndDescs.put("t_function","菜单信息");
        tableAndDescs.put("t_role","角色信息");
        tableAndDescs.put("t_role_function_rel","角色功能信息");
        tableAndDescs.put("t_role_user_rel","角色用户信息");
        tableAndDescs.put("t_user","用户信息");
        tableAndDescs.put("t_wx_business_config","微信企业信息");
        tableAndDescs.put("t_wx_business_menu_rel","微信企业菜单关系信息");
        tableAndDescs.put("t_wx_menu","微信菜单信息");
        tableAndDescs.put("t_wx_msg_log","消息信息");
        tableAndDescs.put("t_wx_sign_log","注册信息");


        return tableAndDescs;
    }

    @Override
    public boolean hasView(String tableName) {
        List<String> views = new ArrayList<String>();
        views.add("t_wx_user");
        views.add("t_business");
        views.add("t_deparment");
        return views.contains(tableName);
    }

    public JdbcUtils getJdbcUtils() {
        return new MysqlJdbcUtils();
    }
}
