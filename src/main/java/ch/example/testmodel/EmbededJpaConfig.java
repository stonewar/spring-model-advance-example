package ch.example.testmodel;

import java.util.Properties;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
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
@Configuration
@ComponentScan(basePackages="ch.example.testmodel.dao.imp")
//@EnableTransactionManagement
public class EmbededJpaConfig {
	
	@Bean
	public DataSource dataSource(){
		EmbeddedDatabaseBuilder edb = new EmbeddedDatabaseBuilder();
	    edb.setType(EmbeddedDatabaseType.H2);
	    EmbeddedDatabase db = edb.build();
	    return db;
	}
	
//	private Properties jpaProperties() {
//		Properties properties = new Properties();
//		properties.setProperty("hibernate.hbm2ddl.auto", "create");
//		properties.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
//		properties.setProperty("hibernate.show_sql", "true");
//		properties.setProperty("hibernate.format_sql", "true");
//		return properties;
//	}
	
	@Bean
	public JpaVendorAdapter adapter(){
		HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
		adapter.setDatabase(Database.H2);
		adapter.setShowSql(true);
		adapter.setGenerateDdl(true);
		adapter.setDatabasePlatform("org.hibernate.dialect.H2Dialect");
		return adapter;	
	}
	
	@Bean
	public LocalContainerEntityManagerFactoryBean containerEntityManagerFactoryBean(){
		LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
		emf.setDataSource(dataSource());
//		emf.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		emf.setJpaVendorAdapter(adapter());
//		emf.setJpaProperties(jpaProperties());
		emf.setPackagesToScan(new String[]{ "ch.example.testmodel.model" });
		return emf;
	}
	
	@Bean
    public JpaTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(containerEntityManagerFactoryBean().getObject());
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
}
