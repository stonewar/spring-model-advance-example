package ch.example.testmodel;

import java.util.Properties;
import javax.inject.Inject;
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

/**
 * 
 * @author yandypiedra
 *
 */

//Spring Data JPA by default looks for an EntityManagerFactory named entityManagerFactory
@Configuration
@EnableJpaRepositories(basePackages="ch.example.testmodel.repository", entityManagerFactoryRef="containerEntityManagerFactoryBean")
public class TestModelConfig {
	
	@Bean
	public DataSource dataSource(){
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/test");
		dataSource.setUsername("root");
		dataSource.setPassword("");
		return dataSource;
	}
	
	@Bean
	public JpaVendorAdapter jpaAdapter(){
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
		emf.setJpaVendorAdapter(jpaAdapter());
//		emf.setJpaProperties(properties());
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
