package config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.cfg.AvailableSettings;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import models.FileModel;


@Configuration
@EnableTransactionManagement
@PropertySource(
	value = {"classpath:app.properties"}
)
@ComponentScans(
	value = {
		@ComponentScan("services"),
		@ComponentScan("dao")
	}
)
public class HibernateConfig
{
	@Autowired
	private Environment env;
	
    private Properties getHibernateProperties()
    {
    	Properties properties = new Properties();
    	
    	properties.put(
    		AvailableSettings.DIALECT,
    		env.getRequiredProperty("hibernate.dialect")
    	);
    	properties.put(
    		AvailableSettings.SHOW_SQL,
    		env.getRequiredProperty("hibernate.show_sql")
    	);
    	properties.put(
    	    AvailableSettings.CURRENT_SESSION_CONTEXT_CLASS,
    		env.getRequiredProperty("hibernate.current.session.context.class")
    	);
    	
    	properties.put("hibernate.id.new_generator_mappings","false");
    	
    	return properties;
    }
    
    private DataSource getDataSource() 
    {
    	DriverManagerDataSource dataSource = new DriverManagerDataSource();
    	
    	dataSource.setDriverClassName(
    		env.getRequiredProperty("datasource.driver")
    	);
    	dataSource.setUrl(
    		env.getRequiredProperty("datasource.url")
    	);
    	dataSource.setUsername(
    		env.getRequiredProperty("datasource.username")
    	);
    	dataSource.setPassword(
    		env.getRequiredProperty("datasource.password")
    	);
    	
    	return dataSource;
    }
    
    @Bean
    public LocalSessionFactoryBean getSessionFactory() {
    	LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
    	
    	sessionFactory.setDataSource(getDataSource());
    	sessionFactory.setAnnotatedClasses(
    		FileModel.class
    	);
    	sessionFactory.setPackagesToScan(new String[] {"entities"});
    	sessionFactory.setHibernateProperties(getHibernateProperties());
    	
    	return sessionFactory;
    }
    
    @Bean
    public HibernateTransactionManager getTransactionManager() {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        
        transactionManager.setSessionFactory(
        	getSessionFactory().getObject()
        );
        
        return transactionManager;
    }
}
