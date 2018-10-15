package com.notearena.bd.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;

@Configuration
@EnableTransactionManagement
@PropertySource("classpath:database.properties")
public class DataBaseConfig {
	@Autowired
	Environment environment;
	
    private static final String DATABASE_DRIVER = "db.driver";
    private static final String DATABASE_PASSWORD = "db.password";
    private static final String DATABASE_URL = "db.url";
    private static final String DATABASE_USERNAME = "db.username";
    
    @Bean
	DataSource dataSource() {
		DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
		driverManagerDataSource.setUrl(environment.getProperty(DATABASE_URL));
		System.out.println("DB Url: "+environment.getProperty(DATABASE_URL));
		driverManagerDataSource.setUsername(environment.getProperty(DATABASE_USERNAME));
		driverManagerDataSource.setPassword(environment.getProperty(DATABASE_PASSWORD));
		driverManagerDataSource.setDriverClassName(environment.getProperty(DATABASE_DRIVER));
		return driverManagerDataSource;
	}
    
    @Bean
    public LocalSessionFactoryBean sessionFactory() {
       LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
       sessionFactory.setDataSource(dataSource());
       sessionFactory.setPackagesToScan(
         new String[] { "com.notearena.bd.model" });
       sessionFactory.setHibernateProperties(hibernateProperties());  
       return sessionFactory;
    }
    
    @Bean
    @Autowired
    public HibernateTransactionManager transactionManager(
      SessionFactory sessionFactory) {
   
       HibernateTransactionManager txManager
        = new HibernateTransactionManager();
       txManager.setSessionFactory(sessionFactory);
  
       return txManager;
    }
    
    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
       return new PersistenceExceptionTranslationPostProcessor();
    }
    
    Properties hibernateProperties() {
        return new Properties() {
           /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

		{
              setProperty("hibernate.hbm2ddl.auto",
            		  environment.getProperty("hibernate.hbm2ddl.auto"));
              setProperty("hibernate.dialect",
            		  environment.getProperty("hibernate.dialect"));
              setProperty("hibernate.show_sql",
            		  environment.getProperty("hibernate.show_sql"));
              setProperty("hibernate.format_sql",
                    		  environment.getProperty("hibernate.format_sql"));
              setProperty("hibernate.globally_quoted_identifiers",
               "true");
           }
        };
     }
}
