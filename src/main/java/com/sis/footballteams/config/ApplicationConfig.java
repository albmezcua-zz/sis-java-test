package com.sis.footballteams.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@Slf4j
@EnableJpaRepositories( entityManagerFactoryRef = "footballTeamEntityManagerFactory",
        transactionManagerRef = "footballTeamsTransactionManager",
        basePackages ={ApplicationConfig.DAO_PACKAGE})
@ComponentScan(value = {ApplicationConfig.MAIN_PACKAGE})
@EnableTransactionManagement
@PropertySource("classpath:application.properties")
public class ApplicationConfig  {

    @Autowired
    private DataSource datasource;

    static final String MAIN_PACKAGE = "com.sis.footballteams.service";
    private static final String ENTITIES_PACKAGE = "com.sis.footballteams.dbFootballTeam.entity";
    static final String DAO_PACKAGE = "com.sis.footballteams.dbFootballTeam.dao";

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }


    @Bean(name="footballTeamsTransactionManager")
    public PlatformTransactionManager transactionManager() {
        return new JpaTransactionManager(entityManagerFactory());
    }

    @Bean
    public JpaVendorAdapter hibernateVendorAdapter() {
        HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
        hibernateJpaVendorAdapter.setShowSql(false);
        hibernateJpaVendorAdapter.setGenerateDdl(false);
        hibernateJpaVendorAdapter.setDatabase(Database.HSQL);
        return hibernateJpaVendorAdapter;
    }

    @Bean
    public EntityManager entityManager(){
        return entityManagerFactory().createEntityManager();
    }

    @Bean(name="footballTeamEntityManagerFactory")
    public EntityManagerFactory entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
        if(datasource!=null){
            entityManagerFactory.setDataSource(datasource);
        }
        entityManagerFactory.setPackagesToScan(ENTITIES_PACKAGE);
        entityManagerFactory.setJpaVendorAdapter(hibernateVendorAdapter());
        entityManagerFactory.setPersistenceUnitName("football-teams");
        entityManagerFactory.afterPropertiesSet();
        Map<String,String> map=new HashMap<String,String>();
        map.put("hibernate.connection.release_mod","after_transaction");
        entityManagerFactory.setJpaPropertyMap(map);
        return entityManagerFactory.getObject();
    }

}
