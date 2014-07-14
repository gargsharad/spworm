package com.spworm.config.infrastructure;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.support.lob.DefaultLobHandler;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = {"com.spworm.model.domain"})
public class PersistenceConfig {

	Logger logger = Logger.getLogger(PersistenceConfig.class);

	@Autowired
	private Environment env;

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		logger.debug("Creating LocalContainerEntityManagerFactoryBean");
		LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
		emf.setJtaDataSource(appDataSource());
		emf.setPackagesToScan(new String[] { "com.sporm.model" });
		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		emf.setJpaVendorAdapter(vendorAdapter);
		emf.setJpaPropertyMap(additionalProperties());
		logger.debug("Complete Creating LocalContainerEntityManagerFactoryBean");
		return emf;
	}

	@Bean
	public DataSource appDataSource() {
		logger.debug("Initializing Data source");
		BasicDataSource appDataSource = new BasicDataSource();
		appDataSource.setUrl(env.getProperty("jdbc.url"));
		appDataSource.setUsername(env.getProperty("user"));
		appDataSource.setUsername(env.getProperty("passord"));
		appDataSource.setDriverClassName(env.getProperty("jdbc.driverClassName"));
		return appDataSource;
	}

	@Bean
	public PlatformTransactionManager transactionManager(
			EntityManagerFactory entityManagerFactory) {
		logger.debug("Creating JpaTransactionManager");
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory);
		logger.debug("Complete Creating JpaTransactionManager");
		return transactionManager;
	}
	
	@Bean
	public DefaultLobHandler defaultLobHandler(EntityManagerFactory entityManagerFactory) {
		return new DefaultLobHandler();
	}

	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
		logger.debug("Complete Creating PersistenceExceptionTranslationPostProcessor");
		return new PersistenceExceptionTranslationPostProcessor();
	}

	Map<String, String> additionalProperties() {

		Map<String, String> jpaPropertyMap = new HashMap<>();
		jpaPropertyMap.put("hibernate.show_sql",
				env.getProperty("hibernate.show_sql"));
		jpaPropertyMap.put("hibernate.dialect",
				env.getProperty("hibernate.dialect"));
		jpaPropertyMap.put("hibernate.hbm2ddl.auto",
				env.getProperty("hibernate.hbm2ddl.auto"));
		jpaPropertyMap.put("hibernate.show_sql",
				env.getProperty("hibernate.show_sql"));

		return jpaPropertyMap;
	}
}
