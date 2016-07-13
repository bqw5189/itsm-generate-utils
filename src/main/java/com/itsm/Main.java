package com.itsm;

import com.itsm.entity.Entity;
import com.itsm.generate.ItsmGenerate;
import com.itsm.generate.PetGenerate;
import com.itsm.jdbc.JdbcUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.lilystudio.smarty4j.Context;
import org.lilystudio.smarty4j.Engine;
import org.lilystudio.smarty4j.Template;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by itsm on 16/4/18.
 */
public class Main {
    public static void main(String[] args){
        ItsmGenerate itsmGenerate = new ItsmGenerate();
        itsmGenerate.generate();
    }
}
