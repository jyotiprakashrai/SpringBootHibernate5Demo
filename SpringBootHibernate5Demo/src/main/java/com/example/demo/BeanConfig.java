package com.example.demo;

/*import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManagerFactory;


@Configuration
public class BeanConfig {

	@Autowired
	private EntityManagerFactory entityManagerFactory;

	@Bean
	public SessionFactory getSessionFactory() {
	    if (entityManagerFactory.unwrap(SessionFactory.class) == null) {
	        throw new NullPointerException("factory is not a hibernate factory");
	    }
	    return entityManagerFactory.unwrap(SessionFactory.class);
	}

}*/


import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.SpringSessionContext;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaSessionFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
public class BeanConfig {

@Bean
public DataSource getDatasource() {
	DriverManagerDataSource dmdatasource=new DriverManagerDataSource();
	dmdatasource.setDriverClassName("com.mysql.jdbc.Driver");
	dmdatasource.setUrl("jdbc:mysql://localhost:3306/test");
	dmdatasource.setUsername("root");
	dmdatasource.setPassword("root");
	return dmdatasource;
}

@Bean
protected Properties hibenateConfig() {
	Properties hibernateProperties=new Properties();
	hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
	hibernateProperties.setProperty("hibernate.show_sql", "true");
	hibernateProperties.setProperty("hibernate.use_sql_comments", "true");
	hibernateProperties.setProperty("hibernate.format_sql", "false");
	hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "update");
	hibernateProperties.setProperty("hibernate.generate_statistics", "false");
	hibernateProperties.setProperty("javax.persistence.validation.mode", "none");
	hibernateProperties.setProperty("org.hibernate.envers.store_data_at_delete", "true");
	hibernateProperties.setProperty("org.hibernate.envers.global_with_modified_flag", "true");
	hibernateProperties.setProperty("hibernate.current_session_context_class", SpringSessionContext.class.getName());

	return hibernateProperties;
}

@Bean
public HibernateJpaSessionFactoryBean sessionFactory() {
	return new HibernateJpaSessionFactoryBean();
}

@Bean
public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
	LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
	
	entityManagerFactoryBean.setDataSource(getDatasource());
	entityManagerFactoryBean.setJpaProperties(hibenateConfig());
	
	HibernateJpaVendorAdapter jpaVendorAdapter=new HibernateJpaVendorAdapter();
	 jpaVendorAdapter.setDatabase(Database.MYSQL);
	
	entityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter);;
	entityManagerFactoryBean.setPackagesToScan("com.example.demo.model");
	return entityManagerFactoryBean;
	
}


@Bean(name = "viewResolver")
public InternalResourceViewResolver getViewResolver() {
	InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
	viewResolver.setPrefix("/WEB-INF/jsp/");
	viewResolver.setSuffix(".jsp");
	return viewResolver;
}

}
