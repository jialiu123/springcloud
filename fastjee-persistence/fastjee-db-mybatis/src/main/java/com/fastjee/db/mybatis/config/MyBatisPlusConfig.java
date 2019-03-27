/*
 * Copyright 2018 吴汶泽(wenzewoo@gmail.com)
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.fastjee.db.mybatis.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.entity.GlobalConfiguration;
import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.spring.MybatisSqlSessionFactoryBean;
import lombok.extern.log4j.Log4j;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import java.sql.SQLException;

/**
 * @author by wenzewoo on 2018/2/5.
 */
@Log4j
@Configuration
public class MyBatisPlusConfig {
    static final String MB_CONFIG = "classpath:mybatis-config.xml";
    static final String MB_MAPPER_XML = "classpath:mapper/*.xml";
    static final String MB_MAPPER_PACKAGE = "com.fastjee.**.mapper";
    static final String MB_MODEL_PACKAGE = "com.fastjee.**.entity";
    static final String MB_ENUMS_PACKAGE = "com.fastjee.**.entity.enums";

    @Bean(name = "defaultDataSource", initMethod = "init", destroyMethod = "close")
    public DruidDataSource defaultDataSource(
            @Value("${persistence.mybatis.jdbc.driver}") String driver,
            @Value("${persistence.mybatis.jdbc.url}") String url,
            @Value("${persistence.mybatis.jdbc.username}") String username,
            @Value("${persistence.mybatis.jdbc.password}") String password,
            @Value("${persistence.mybatis.druid.initialSize}") Integer initialSize,
            @Value("${persistence.mybatis.druid.minIdle}") Integer minIdle,
            @Value("${persistence.mybatis.druid.maxActive}") Integer maxActive,
            @Value("${persistence.mybatis.druid.maxWait}") Integer maxWait,
            @Value("${persistence.mybatis.druid.timeBetweenEvictionRunsMillis}") Integer timeBetweenEvictionRunsMillis,
            @Value("${persistence.mybatis.druid.minEvictableIdleTimeMillis}") Integer minEvictableIdleTimeMillis,
            @Value("${persistence.mybatis.druid.validationQuery}") String validationQuery,
            @Value("${persistence.mybatis.druid.testWhileIdle}") Boolean testWhileIdle,
            @Value("${persistence.mybatis.druid.testOnBorrow}") Boolean testOnBorrow,
            @Value("${persistence.mybatis.druid.testOnReturn}") Boolean testOnReturn,
            @Value("${persistence.mybatis.druid.poolPreparedStatements}") Boolean poolPreparedStatements,
            @Value("${persistence.mybatis.druid.maxPoolPreparedStatementPerConnectionSize}") Integer maxPoolPreparedStatementPerConnectionSize,
            @Value("${persistence.mybatis.druid.filters}") String filters) {
        log.info("init defaultDataSource");
        try {
            DruidDataSource dataSource = new DruidDataSource();
            dataSource.setDriverClassName(driver);
            dataSource.setUrl(url);
            dataSource.setUsername(username);
            dataSource.setPassword(password);
            dataSource.setInitialSize(initialSize);
            dataSource.setMinIdle(minIdle);
            dataSource.setMaxActive(maxActive);
            dataSource.setMaxWait(maxWait);
            dataSource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
            dataSource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
            dataSource.setValidationQuery(validationQuery);
            dataSource.setTestWhileIdle(testWhileIdle);
            dataSource.setTestOnBorrow(testOnBorrow);
            dataSource.setTestOnReturn(testOnReturn);
            dataSource.setPoolPreparedStatements(poolPreparedStatements);
            dataSource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);
            dataSource.setFilters(filters);
            return dataSource;
        } catch (SQLException e) {
            log.error("defaultDataSource failed to load" ,e);
        }
        return null;
    }

    @Bean(name = "mybatisGlobalConfiguration")
    public GlobalConfiguration mybatisGlobalConfiguration(
            @Value("${persistence.mybatis.plusConfig.idType}") Integer idType,
            @Value("${persistence.mybatis.plusConfig.dbColumnUnderline}") Boolean dbColumnUnderline,
            @Value("${persistence.mybatis.plusConfig.isCapitalMode}") Boolean isCapitalMode) {
        log.info("init mybatisGlobalConfiguration");
        GlobalConfiguration globalConfig = new GlobalConfiguration();
        // 主键类型
        if (idType != null) {
            globalConfig.setIdType(idType);
        }
        // 驼峰下划线转换
        if (dbColumnUnderline != null) {
            globalConfig.setDbColumnUnderline(dbColumnUnderline);
        }
        // 数据库大写下划线转换
        if (isCapitalMode != null) {
            globalConfig.setCapitalMode(isCapitalMode);
        }
        // 自动填充公用字段值
        globalConfig.setMetaObjectHandler(new MybatisMetaObjectHandler());
        return globalConfig;
    }

    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(
            @Qualifier("mybatisGlobalConfiguration") GlobalConfiguration globalConfig,
            @Qualifier("defaultDataSource") DruidDataSource dataSource) throws Exception {
        log.info("init sqlSessionFactory");
        MybatisSqlSessionFactoryBean sqlSessionFactory = new MybatisSqlSessionFactoryBean();
        // 数据源
        sqlSessionFactory.setDataSource(dataSource);
        // 全局配置
        sqlSessionFactory.setGlobalConfig(globalConfig);
        Interceptor[] interceptor = {new PaginationInterceptor()};
        // 分页插件
        sqlSessionFactory.setPlugins(interceptor);
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        try {
            // mybatis原生配置文件
            sqlSessionFactory.setConfigLocation(resolver.getResource(MB_CONFIG));
            // 扫描Mapper文件
            sqlSessionFactory.setMapperLocations(resolver.getResources(MB_MAPPER_XML));
            // 扫描实体包
            sqlSessionFactory.setTypeAliasesPackage(MB_MODEL_PACKAGE);
            // 扫描枚举包
            sqlSessionFactory.setTypeEnumsPackage(MB_ENUMS_PACKAGE);
            return sqlSessionFactory.getObject();
        } catch (Exception e) {
            log.error("SqlSessionFactory failed to load", e);
        }
        return null;
    }

    @Bean(name = "mapperScannerConfigurer")
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        // 扫描Mapper接口
        mapperScannerConfigurer.setBasePackage(MB_MAPPER_PACKAGE);
        return mapperScannerConfigurer;
    }

    @Bean(name = "transactionManager")
    public DataSourceTransactionManager transactionManager(
            @Qualifier("defaultDataSource") DruidDataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}
