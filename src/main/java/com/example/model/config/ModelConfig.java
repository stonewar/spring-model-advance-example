package com.example.model.config;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.support.PersistenceAnnotationBeanPostProcessor;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 
 * @author yandypiedra
 *
 */

//Spring Data JPA looks by default for an EntityManagerFactory with id entityManagerFactory
@Configuration
@PropertySource("classpath:properties/db.properties")
@EnableJpaRepositories(basePackages="com.example.model.repository", entityManagerFactoryRef="containerEntityManagerFactoryBean")
public class ModelConfig {
	
	//TODO injection is not working!
	@Autowired
	private Environment env;
	
	@Bean
	public DataSource dataSource(){
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/CD");
		dataSource.setUsername("root");
		dataSource.setPassword("");
		return dataSource;
	}
	
	@Bean
	public JpaVendorAdapter jpaVendorAdapter(){
		HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
		adapter.setDatabase(Database.MYSQL);
		adapter.setShowSql(true);
		adapter.setGenerateDdl(true);
		return adapter;	
	}
	
	@Bean
	public LocalContainerEntityManagerFactoryBean containerEntityManagerFactoryBean(){
		LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
		emf.setDataSource(dataSource());
		emf.setJpaVendorAdapter(jpaVendorAdapter());
//		emf.setJpaProperties(properties());
		emf.setPackagesToScan(new String[]{ "com.example.model" });
		return emf;
	}
	
	@Bean
    public JpaTransactionManager transactionManager(LocalContainerEntityManagerFactoryBean entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory.getObject());
        return transactionManager;
    }
 
	@Bean
	public PersistenceAnnotationBeanPostProcessor paPostProcessor() {
	  return new PersistenceAnnotationBeanPostProcessor();
	}
	
	@Bean
	public BeanPostProcessor persistenceTranslation() {
	  return new PersistenceExceptionTranslationPostProcessor();
	}
	
	
//	private Properties properties() {
//		Properties properties = new Properties();
//		properties.put("hibernate.dialect",
//				"org.hibernate.dialect.MySQLDialect");
//		properties.put("hibernate.show_sql", true);
//		properties.put("hibernate.hbm2ddl.auto", "create");
//		return properties;
//
//	}
	
}
