package com.itsm.generate;

import com.itsm.jdbc.JdbcUtils;
import com.itsm.jdbc.MysqlJdbcUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by tom on 16/7/13.
 */
public class ItsmIppowerGenerate extends AbstractGenerate{
    public String getOutPath() {
        return "/work/001_code/github/java/uics/uics-ippower/";
    }

    public String getPackagePath() {
        return "/com/uics/ippower/";
    }

    public String getPackage() {
        return "com.uics.ippower.";
    }

    public String getArtifactId() {
        return "uics-ippower-";
    }

    public Map<String, String> tableAndDescs() {
        Map<String, String> tableAndDescs = new HashMap<String, String>();
//        tableAndDescs.put("sy_area", "区域");
        tableAndDescs.put("t_deviceparam", "设备参数");
        return tableAndDescs;
    }

    public JdbcUtils getJdbcUtils() {
        return new MysqlJdbcUtils();
    }

    public static void main(String[] args){
        ItsmIppowerGenerate itsmGenerate = new ItsmIppowerGenerate();
        itsmGenerate.generate();
    }
}

