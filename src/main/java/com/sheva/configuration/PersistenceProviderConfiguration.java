package com.sheva.configuration;

import org.hibernate.SessionFactory;
import org.hibernate.jpamodelgen.xml.jaxb.Persistence;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

@Configuration
public class PersistenceProviderConfiguration {

    @Bean(name = "sessionFactory")
    public SessionFactory getSessionFactory(DataSource dataSource) throws IOException {

        LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();

        factoryBean.setPackagesToScan("com.sheva");
        factoryBean.setDataSource(dataSource);
        factoryBean.setHibernateProperties(getAdditionalProperties());
        factoryBean.afterPropertiesSet();

        return factoryBean.getObject();
    }

    private Properties getAdditionalProperties(){

        Properties properties = new Properties();

        properties.put("hibernate.show_sql", "true");
        properties.put("hibernate.default_schema", "training_place");
        properties.put("current_session_context_class", "org.springframework.orm.hibernate5.SpringSessionContext");

        return properties;
    }
}
