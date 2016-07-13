package com.itsm.generate;

import com.itsm.entity.Entity;
import com.itsm.jdbc.JdbcUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
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
 * Created by tom on 16/7/13.
 */
public abstract class AbstractGenerate {
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractGenerate.class);

    /**
     * 包名 路径 eg. "/work/001_code/github/java/itsm-platform/itsm-account/"
     *
     * @return
     */
    public abstract String getOutPath();

    public String getOutApiPath(){
        return getOutModulePath("api");
    }
    public String getOutConsumerPath(){
        return getOutModulePath("consumer");
    }
    public String getOutConsumerTestPath(){
        return getOutModuleTestPath("consumer");
    }
    public String getOutServiceProviderPath(){
        return getOutModulePath("service-provider");
    }
    public String getOutRestProviderPath(){
        return getOutModulePath("rest-provider");
    }
    public String getOutServiceProviderTestPath(){
        return getOutModuleTestPath("service-provider");
    }

    public String getOutModulePath(String moduleName){
        return getOutPath() + getArtifactId() + moduleName+"/src/main/java" + getPackagePath();
    }
    public String getOutModuleTestPath(String moduleName){
        return getOutPath() + getArtifactId() + moduleName+"/src/test/java" + getPackagePath();
    }

    /**
     * 包名 路径 eg. "/com/itsm/platform/account/"
     *
     * @return
     */
    public abstract String getPackagePath();

    /**
     * 包名 eg. "com.itsm.platform.account."
     *
     * @return
     */
    public abstract String getPackage();

    /**
     * 项目 名称 eg. "itsm-account-"
     *
     * @return
     */
    public abstract String getArtifactId();

    /**
     * 生成 表名 及 描述
     *
     * @return
     */
    public abstract Map<String, String> tableAndDescs();

    public abstract JdbcUtils getJdbcUtils();

    public void generate() {

        for (Map.Entry<String, String> tableAndDesc : this.tableAndDescs().entrySet()) {
            Entity entity = new Entity();

            entity.setTableName(tableAndDesc.getKey());
            entity.setEntityName(tableAndDesc.getValue());

            //初始化字段
            initField(entity);

            //生成entity
            generateEntity(entity);

            //xml
            updateXml(entity);
        }
    }

    public void updateXml(Entity entity) {
        String serviceXml = getOutPath() + getArtifactId() + "service-provider/src/main/resources/META-INF/spring/" + getArtifactId() + "service-provider.xml";
        String restXml = getOutPath() + getArtifactId() + "rest-provider/src/main/resources/META-INF/spring/" + getArtifactId() + "rest-provider.xml";
        String consumerXml = getOutPath() + getArtifactId() + "consumer/src/main/resources/META-INF/spring/" + getArtifactId() + "consumer.xml";
        String actionXml = getOutPath() + getArtifactId() + "consumer/src/main/resources/META-INF/spring/" + getArtifactId() + "action.xml";

        try {
            List<String> serviceXmlList = FileUtils.readLines(new File(serviceXml));
            if (!isExist(serviceXmlList, "id=\"" + entity.getFieldClassName() + "Service\"")) {
                serviceXmlList.add(serviceXmlList.size() - 1, getServiceXml(entity));
                FileUtils.writeLines(new File(serviceXml), serviceXmlList);
            }

            List<String> consumerXmlList = FileUtils.readLines(new File(consumerXml));
            if (!isExist(consumerXmlList, "id=\"" + entity.getFieldClassName() + "Service\"")) {
                consumerXmlList.add(consumerXmlList.size() - 1, getConsumerXml(entity));
                FileUtils.writeLines(new File(consumerXml), consumerXmlList);
            }


            List<String> actionXmlList = FileUtils.readLines(new File(actionXml));
            if (!isExist(consumerXmlList, "class=\"" + getPackage() + "consumer." + entity.getEntityClassName() + "DemoAction\"")) {
                actionXmlList.add(actionXmlList.size() - 1, getActionXml(entity));
                FileUtils.writeLines(new File(actionXml), actionXmlList);
            }

            List<String> restXmlList = FileUtils.readLines(new File(restXml));
            if (!isExist(restXmlList, "id=\"" + entity.getFieldClassName() + "RestService\"")) {
                restXmlList.add(restXmlList.size() - 1, getRestXml(entity));
                FileUtils.writeLines(new File(restXml), restXmlList);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getActionXml(Entity entity) {
        return "\n\t<!-- " + entity.getEntityName() + " action -->\n" +
                "    <bean class=\"" + getPackage() + "consumer." + entity.getEntityClassName() + "DemoAction\" init-method=\"start\">\n" +
                "        <property name=\"" + entity.getFieldClassName() + "Service\" ref=\"" + entity.getFieldClassName() + "Service\"/>\n" +
                "    </bean>\n" +
                "    <!-- " + entity.getEntityName() + " action -->\n";
    }

    public String getConsumerXml(Entity entity) {
        return "\n\t<!-- " + entity.getEntityName() + " service -->\n" +
                "    <dubbo:reference id=\"" + entity.getFieldClassName() + "Service\" interface=\"" + getPackage() + "service." + entity.getEntityClassName() + "Service\"/>\n" +
                "    <!-- " + entity.getEntityName() + " service -->\n";
    }

    private boolean isExist(List<String> serviceXmlList, String text) {
        for (String line : serviceXmlList) {
            if (line.indexOf(text) > -1) {
                return true;
            }
        }
        return false;
    }

    public String getRestXml(Entity entity) {
        return "\n\t<!--" + entity.getEntityName() + " api -->\n" +
                "    <dubbo:service interface=\"" + getPackage() + "facade." + entity.getEntityClassName() + "RestService\" ref=\"" + entity.getFieldClassName() + "RestService\"\n" +
                "                   protocol=\"rest\" validation=\"true\" timeout=\"2000\" connections=\"100\"/>\n" +
                "\n" +
                "    <bean id=\"" + entity.getFieldClassName() + "RestService\" class=\"" + getPackage() + "facade." + entity.getEntityClassName() + "RestServiceImpl\">\n" +
                "        <property name=\"" + entity.getFieldClassName() + "Service\" ref=\"" + entity.getFieldClassName() + "Service\"/>\n" +
                "    </bean>\n" +
                "\n" +
                "    <dubbo:reference id=\"" + entity.getFieldClassName() + "Service\" interface=\"" + getPackage() + ".service." + entity.getEntityClassName() + "Service\"/>\n" +
                "    <!--" + entity.getEntityName() + " api -->\n";
    }

    public String getServiceXml(Entity entity) {
        return "\n\t<!-- " + entity.getEntityName() + " api -->\n" +
                "    <bean id=\"" + entity.getFieldClassName() + "Service\" class=\"" + getPackage() + "service." + entity.getEntityClassName() + "ServiceImpl\"/>\n" +
                "\n" +
                "    <dubbo:service interface=\"" + getPackage() + "service." + entity.getEntityClassName() + "Service\" ref=\"" + entity.getFieldClassName() + "Service\"\n" +
                "                   protocol=\"dubbo\" validation=\"true\" timeout=\"2000\" connections=\"100\"/>\n" +
                "    <!-- " + entity.getEntityName() + " api -->\n";
    }


    public void generateEntity(Entity entity) {
        Map<String, String> outFileNameAndTemplateNames = new HashMap<String, String>();
        //api
        outFileNameAndTemplateNames.put(outFileName(getOutApiPath(), "entity", entity, ".java"), "api/entity/Entity.tpl");
        outFileNameAndTemplateNames.put(outFileName(getOutApiPath(), "facade", entity, "RestService.java"), "api/facade/RestService.tpl");
        outFileNameAndTemplateNames.put(outFileName(getOutApiPath(), "service", entity, "Service.java"), "api/service/Service.tpl");

        //consumer
        outFileNameAndTemplateNames.put(outFileName(getOutConsumerPath(), "consumer", entity, "DemoAction.java"), "consumer/DemoAction.tpl");
        outFileNameAndTemplateNames.put(outFileName(getOutConsumerTestPath(), "restclient", entity, "RestClient.java"), "consumer/RestClient.tpl");

        //service provider
        outFileNameAndTemplateNames.put(outFileName(getOutServiceProviderPath(), "repository", entity, "Dao.java"), "service/Dao.tpl");
        outFileNameAndTemplateNames.put(outFileName(getOutServiceProviderPath(), "service", entity, "ServiceImpl.java"), "service/ServiceImpl.tpl");
        outFileNameAndTemplateNames.put(outFileName(getOutServiceProviderTestPath(), "repository", entity, "DaoTest.java"), "test/repository/TestDao.tpl");

        //rest provider
        outFileNameAndTemplateNames.put(outFileName(getOutRestProviderPath(), "facade", entity, "RestServiceImpl.java"), "rest/RestServiceImpl.tpl");

        for(Map.Entry<String, String> outFileNameAndTemplateName: outFileNameAndTemplateNames.entrySet()){
            outTemplate(entity, outFileNameAndTemplateName.getKey(), outFileNameAndTemplateName.getValue());
        }
    }

    private void outTemplate(Entity entity, String outFilePath, String templateName) {
        File outFile = new File(outFilePath);
        if (!outFile.exists()) {
            renderTemplate(entity, templateName, outFile);
            LOGGER.info("{} template render:{}", templateName, outFilePath);
        }
    }

    private String outFileName(String outModulePath, String lastPackageName, Entity entity, String endFileName) {
        return outModulePath + File.separatorChar + lastPackageName + File.separatorChar + entity.getEntityClassName() + endFileName;
    }



    public void renderTemplate(Entity entity, String templateName, final File outFile) {
        Map<String, String> config = new HashMap<String, String>();
        config.put("template.path", "src/main/resources/template/");
        config.put("encoding", "utf-8");

        Engine smartyEngine = new Engine(config);
        smartyEngine.setLeftDelimiter("<#");
        smartyEngine.setRightDelimiter("#>");

        FileOutputStream fileOutputStream = null;

        try {
            fileOutputStream = new FileOutputStream(outFile);

            Template template = smartyEngine.getTemplate(templateName);

            Context context = new Context();
            context.set("entity", entity);
            context.set("package", getPackage());
            context.set("auth", "tom");
            context.set("date", DateFormatUtils.format(System.currentTimeMillis(), "yyyy-MM-dd HH:mm:ss"));
            template.merge(context, fileOutputStream);

        }catch (Exception e){
            LOGGER.warn("render error!", e);
        }finally {
            IOUtils.closeQuietly(fileOutputStream);
        }

    }

    public void initField(Entity entity) {
        try {
            entity.setFields(this.getJdbcUtils().desc(entity.getTableName()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
