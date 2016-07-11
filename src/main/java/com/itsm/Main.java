package com.itsm;

import com.itsm.entity.Entity;
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

    public static final String PACKAGE_PATH = "/com/itsm/platform/account/";
    private static Logger logger = LoggerFactory.getLogger(Main.class);
    private static final String PACKAGE = "com.itsm.platform.account.";
    public static final String ARTIFACT_ID = "itsm-account-";
    public static void main(String[] args){
        Entity entity = new Entity();
        entity.setTableName("t_um_user");
        entity.setEntityName("用户");

        //初始化字段
        initField(entity);

        //生成entity
        generateEntity(entity);

        //xml
        updateXml(entity);
    }

    private static void updateXml(Entity entity) {
        String serviceXml = Entity.OUT_PATH + ARTIFACT_ID +"service-provider/src/main/resources/META-INF/spring/"+ARTIFACT_ID +"service-provider.xml";
        String restXml = Entity.OUT_PATH + ARTIFACT_ID +"rest-provider/src/main/resources/META-INF/spring/"+ARTIFACT_ID +"rest-provider.xml";
        String consumerXml = Entity.OUT_PATH + ARTIFACT_ID +"consumer/src/main/resources/META-INF/spring/"+ARTIFACT_ID +"consumer.xml";
        String actionXml = Entity.OUT_PATH + ARTIFACT_ID +"consumer/src/main/resources/META-INF/spring/"+ARTIFACT_ID +"action.xml";

        try {
            List<String> serviceXmlList = FileUtils.readLines(new File(serviceXml));
            if (!isExist(serviceXmlList,"id=\"" + entity.getFieldClassName() + "Service\"")) {
                serviceXmlList.add(serviceXmlList.size() - 1, getServiceXml(entity));
                FileUtils.writeLines(new File(serviceXml), serviceXmlList);
            }

            List<String> consumerXmlList = FileUtils.readLines(new File(consumerXml));
            if (!isExist(consumerXmlList,"id=\"" + entity.getFieldClassName() + "Service\"")) {
                consumerXmlList.add(consumerXmlList.size() - 1, getConsumerXml(entity));
                FileUtils.writeLines(new File(consumerXml), consumerXmlList);
            }


            List<String> actionXmlList = FileUtils.readLines(new File(actionXml));
            if (!isExist(consumerXmlList,"class=\""+PACKAGE+"consumer."+entity.getEntityClassName()+"DemoAction\"")) {
                actionXmlList.add(actionXmlList.size() - 1, getActionXml(entity));
                FileUtils.writeLines(new File(actionXml), actionXmlList);
            }

            List<String> restXmlList  = FileUtils.readLines(new File(restXml));
            if (!isExist(restXmlList,"id=\"" + entity.getFieldClassName() + "RestService\"")) {
                restXmlList.add(restXmlList.size() - 1, getRestXml(entity));
                FileUtils.writeLines(new File(restXml), restXmlList);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String getActionXml(Entity entity) {
        return "\n\t<!-- "+entity.getEntityName()+" action -->\n" +
                "    <bean class=\""+PACKAGE+"consumer."+entity.getEntityClassName()+"DemoAction\" init-method=\"start\">\n" +
                "        <property name=\""+entity.getFieldClassName()+"Service\" ref=\""+entity.getFieldClassName()+"Service\"/>\n" +
                "    </bean>\n" +
                "    <!-- "+entity.getEntityName()+" action -->\n";
    }

    private static String getConsumerXml(Entity entity) {
        return "\n\t<!-- "+entity.getEntityName()+" service -->\n" +
                "    <dubbo:reference id=\""+entity.getFieldClassName()+"Service\" interface=\""+PACKAGE+"service."+entity.getEntityClassName()+"Service\"/>\n" +
                "    <!-- "+entity.getEntityName()+" service -->\n";
    }

    private static boolean isExist(List<String> serviceXmlList, String text) {
        for (String line:serviceXmlList){
            if (line.indexOf(text)>-1){
                return true;
            }
        }
        return false;
    }

    private static String getRestXml(Entity entity) {
        return "\n\t<!--"+entity.getEntityName()+" api -->\n" +
                "    <dubbo:service interface=\""+PACKAGE+"facade."+entity.getEntityClassName()+"RestService\" ref=\""+entity.getFieldClassName()+"RestService\"\n" +
                "                   protocol=\"rest\" validation=\"true\" timeout=\"2000\" connections=\"100\"/>\n" +
                "\n" +
                "    <bean id=\""+entity.getFieldClassName()+"RestService\" class=\""+PACKAGE+"facade."+entity.getEntityClassName()+"RestServiceImpl\">\n" +
                "        <property name=\""+entity.getFieldClassName()+"Service\" ref=\""+entity.getFieldClassName()+"Service\"/>\n" +
                "    </bean>\n" +
                "\n" +
                "    <dubbo:reference id=\""+entity.getFieldClassName()+"Service\" interface=\""+PACKAGE+".service."+entity.getEntityClassName()+"Service\"/>\n" +
                "    <!--"+entity.getEntityName()+" api -->\n";
    }

    private static String getServiceXml(Entity entity) {
        return "\n\t<!-- "+entity.getEntityName()+" api -->\n" +
                "    <bean id=\""+entity.getFieldClassName()+"Service\" class=\""+PACKAGE+"service."+entity.getEntityClassName()+"ServiceImpl\"/>\n" +
                "\n" +
                "    <dubbo:service interface=\""+PACKAGE+"service."+entity.getEntityClassName()+"Service\" ref=\""+entity.getFieldClassName()+"Service\"\n" +
                "                   protocol=\"dubbo\" validation=\"true\" timeout=\"2000\" connections=\"100\"/>\n" +
                "    <!-- "+entity.getEntityName()+" api -->\n";
    }


    private static void generateEntity(Entity entity) {
        FileOutputStream fileOutputStream = null;
        try {
            File outEntityFile = new File(entity.OUT_API_PATH + File.separatorChar + "entity" + File.separatorChar + entity.getEntityClassName() + ".java");
            File outFacadeFile = new File(entity.OUT_API_PATH + File.separatorChar + "facade" + File.separatorChar + entity.getEntityClassName() + "RestService.java");
            File outServiceFile = new File(entity.OUT_API_PATH + File.separatorChar + "service" + File.separatorChar + entity.getEntityClassName() + "Service.java");
            File outConsumerServiceFile = new File(entity.OUT_CONSUMER_PATH + File.separatorChar + "consumer" + File.separatorChar + entity.getEntityClassName() + "DemoAction.java");
            File outDaoFile = new File(entity.OUT_SERVICE_PATH + File.separatorChar + "repository" + File.separatorChar + entity.getEntityClassName() + "Dao.java");
            File outTestDaoFile = new File(entity.OUT_TEST_DAO_PATH + File.separatorChar + "repository" + File.separatorChar + "Test" + entity.getEntityClassName() + "Dao.java");
            File outServiceImplFile = new File(entity.OUT_SERVICE_PATH + File.separatorChar + "service" + File.separatorChar + entity.getEntityClassName() + "ServiceImpl.java");
            File outRestFile = new File(entity.OUT_REST_PATH + File.separatorChar + "facade" + File.separatorChar + entity.getEntityClassName() + "RestServiceImpl.java");
            File outRestClientFile = new File(entity.OUT_REST_CLIENT_PATH + File.separatorChar + "restclient"+ File.separatorChar + entity.getEntityClassName() + "RestClient.java");

            if (!outEntityFile.exists()) {
                fileOutputStream = new FileOutputStream(outEntityFile);
                renderTemplate(entity, "api/entity/Entity.tpl", fileOutputStream);
                logger.info("api template render:{}", outEntityFile);
            }

            if (!outFacadeFile.exists()) {
                fileOutputStream = new FileOutputStream(outFacadeFile);
                renderTemplate(entity, "api/facade/RestService.tpl", fileOutputStream);
                logger.info("api template render:{}", outFacadeFile);
            }

            if (!outServiceFile.exists()) {
                fileOutputStream = new FileOutputStream(outServiceFile);
                renderTemplate(entity, "api/service/Service.tpl", fileOutputStream);
                logger.info("api template render:{}", outServiceFile);
            }

            if (!outDaoFile.exists()) {
                fileOutputStream = new FileOutputStream(outDaoFile);
                renderTemplate(entity, "service/Dao.tpl", fileOutputStream);
                logger.info("api template render:{}", outDaoFile);
            }

            if (!outServiceImplFile.exists()) {
                fileOutputStream = new FileOutputStream(outServiceImplFile);
                renderTemplate(entity, "service/ServiceImpl.tpl", fileOutputStream);
                logger.info("api template render:{}", outServiceImplFile);
            }

            if (!outRestFile.exists()) {
                fileOutputStream = new FileOutputStream(outRestFile);
                renderTemplate(entity, "rest/RestServiceImpl.tpl", fileOutputStream);
                logger.info("api template render:{}", outRestFile);
            }

            if (!outTestDaoFile.exists()) {
                fileOutputStream = new FileOutputStream(outTestDaoFile);
                renderTemplate(entity, "test/repository/TestDao.tpl", fileOutputStream);
                logger.info("test dao template render:{}", outTestDaoFile);
            }

            if (!outConsumerServiceFile.exists()) {
                fileOutputStream = new FileOutputStream(outConsumerServiceFile);
                renderTemplate(entity, "consumer/DemoAction.tpl", fileOutputStream);
                logger.info("consumer service template render:{}", outConsumerServiceFile);
            }
            if (!outRestClientFile.exists()) {
                fileOutputStream = new FileOutputStream(outRestClientFile);
                renderTemplate(entity, "consumer/RestClient.tpl", fileOutputStream);
                logger.info("rest client template render:{}", outRestClientFile);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(fileOutputStream);
        }
    }


    private static Template renderTemplate(Entity entity, String templateName, final FileOutputStream fileOutputStream) throws Exception {
        Map<String, String> config = new HashMap<String, String>();
        config.put("template.path", "src/main/resources/template/");
        config.put("encoding", "utf-8");

        Engine smartyEngine = new Engine(config);
        smartyEngine.setLeftDelimiter("<#");
        smartyEngine.setRightDelimiter("#>");

        Template template = smartyEngine.getTemplate(templateName);

        Context context = new Context();
        context.set("entity", entity);
        context.set("package", PACKAGE);
        template.merge(context, fileOutputStream);

        return template;
    }

    private static void initField(Entity entity) {
        JdbcUtils jdbcUtils = new JdbcUtils();
        try {
            entity.setFields(jdbcUtils.desc(entity.getTableName()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
