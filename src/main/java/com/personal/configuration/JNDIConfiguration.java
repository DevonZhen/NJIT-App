package com.personal.configuration;


import java.util.Properties;
import javax.sql.DataSource;
import javax.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.instrument.classloading.InstrumentationLoadTimeWeaver;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.orm.hibernate5.HibernateExceptionTranslator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Profile("jndi")
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.personal.repository")
public class JNDIConfiguration {

	@Autowired
	protected Environment env;
	@Value("${init-db:false}")
	private String initDatabase;

	//Set the DataSource [not in a container]
	@Bean	
	public DataSource dataSource() {
		JndiDataSourceLookup dataSourceLookup = new JndiDataSourceLookup();
		DataSource dataSource = dataSourceLookup.getDataSource(env.getProperty("jndiNameDemo"));
		return dataSource;
	}
	
	//Sets the DataSource inside a container
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setDataSource(dataSource());
		factory.setJpaVendorAdapter( getJpaVendorAdapter());
		factory.setPackagesToScan(env.getProperty("domainscancontract").split(","));
		factory.setJpaProperties(getJpaProperties());
		factory.afterPropertiesSet();
		factory.setLoadTimeWeaver(new InstrumentationLoadTimeWeaver());
		return factory;

	}
	
	//Initializes the DataSource
	@Bean
	public DataSourceInitializer dataSourceInitializer(DataSource dataSource) {
		DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
		dataSourceInitializer.setDataSource(dataSource);
		ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
		databasePopulator.addScript(new ClassPathResource("db.sql"));
		dataSourceInitializer.setDatabasePopulator(databasePopulator);
		dataSourceInitializer.setEnabled(Boolean.parseBoolean(initDatabase));
		return dataSourceInitializer;
	}	
	
	//REQUIRED, even with one DB, also used for 2 DB
	@Bean
	public PlatformTransactionManager transactionManager() {
		EntityManagerFactory factory = entityManagerFactory().getObject();
		return new JpaTransactionManager(factory);
	}

	//Sets the method to the NJIT.properties Hibernate setting
	private Properties getJpaProperties() {
		Properties jpaProperties = new Properties();
		jpaProperties.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
		return jpaProperties;
	}
	
	//Sets the method to the NJIT.properties Hibernate setting
	private HibernateJpaVendorAdapter getJpaVendorAdapter() {
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		vendorAdapter.setGenerateDdl(Boolean.parseBoolean(env.getProperty("hibernate.generateDDL")));
		vendorAdapter.setShowSql(Boolean.parseBoolean(env.getProperty("hibernate.showSql")));
		return vendorAdapter;
	}
	
	//Sets the method to the NJIT.properties Hibernate setting
	@Bean
	public HibernateExceptionTranslator hibernateExceptionTranslator() {
		return new HibernateExceptionTranslator();
	}

	
}
