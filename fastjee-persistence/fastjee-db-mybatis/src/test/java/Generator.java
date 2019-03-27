import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author by wenzewoo on 2018/2/5.
 */
public class Generator {

    @Test
    public void generateCode() {
        String outputDir = "E:\\codeGen";

        // 其他包的父路径 -> packageName.entity
        String packageName = "com.fastjee.usercenter";

        // 实体包路径
        String entityPackage = "com.fastjee.common.entity";

        // 需要生成的表
        String[] tables = {"tb_user", "tb_role", "tb_resource","tb_section","tb_system"};

        generateByTables(outputDir,false, packageName,entityPackage, tables);
    }

    private void generateByTables(String outputDir,boolean serviceNameStartWithI, String packageName,final String entityPackage, String... tableNames) {
        GlobalConfig config = new GlobalConfig();
        String dbUrl = "jdbc:mysql://rm-bp1b4y426p7vw48yfgo.mysql.rds.aliyuncs.com:3306/fastjee-usercenter";
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDbType(DbType.MYSQL)
                .setUrl(dbUrl)
                .setUsername("root")
                .setPassword("moba@2016")
                .setDriverName("com.mysql.jdbc.Driver")
                .setTypeConvert(new MySqlTypeConvert(){
                    @Override
                    public DbColumnType processTypeConvert(String fieldType) {
                        return super.processTypeConvert(fieldType);
                    }
                });
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig
                .setCapitalMode(true)
                .setEntityLombokModel(true)
                .setDbColumnUnderline(true)
                .setNaming(NamingStrategy.underline_to_camel)
                .setInclude(tableNames)
                .setRestControllerStyle(true)
                .setSuperEntityClass("com.fastjee.common.entity.base.MpEntity")
                .setSuperEntityColumns("createAt","createDate","modifyAt","modifyDate")
                .setTablePrefix("tb_","sys_");
        config.setActiveRecord(true)
                .setAuthor("liuzhixiang")
                .setOutputDir(outputDir)
                .setEnableCache(true) // XML 二级缓存
                .setBaseResultMap(true)// XML ResultMap
                .setBaseColumnList(true)// XML columList
                .setFileOverride(true);
        if (!serviceNameStartWithI) {
            config.setServiceName("%sService");
        }
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // 注入自定义配置，可以在 VM 中使用 cfg.abc 【可无】
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("entityPackage", entityPackage);
                this.setMap(map);
            }
        };

        List<FileOutConfig> fileOutConfigList = Lists.newArrayList();
        // 调整 entity 生成目录
        fileOutConfigList.add(new FileOutConfig("/templates/entity.java.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return outputDir + "\\" + entityPackage.replaceAll("\\.", "//") + "\\" + tableInfo.getEntityName() + ".java";
            }
        });
        // 关闭entity默认生成目录
        TemplateConfig templateConfig = new TemplateConfig();
        templateConfig.setEntity(null);

        cfg.setFileOutConfigList(fileOutConfigList);

        new AutoGenerator().setGlobalConfig(config)
                .setDataSource(dataSourceConfig)
                .setStrategy(strategyConfig)
                .setTemplate(templateConfig)
                .setPackageInfo(
                        new PackageConfig()
                                .setParent(packageName)
                                .setController("controller")
                                .setEntity("entity")
                )
                .setCfg(cfg)
                .execute();
    }
}
