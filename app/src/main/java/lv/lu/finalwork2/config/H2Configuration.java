package lv.lu.finalwork2.config;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

@EnableTransactionManagement
@Configuration
@PropertySource(value = "classpath:application.properties")
public class H2Configuration {

    @Bean
    public DataSource dataSource(
            @Value("${datasource.initScript}") String initScript
    ) {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .setName("FINAL_WORK")
                .addScript(initScript)
                .build();
    }

    @Bean
    public Properties hibernateProperties(
            @Value("${hibernate.dialect}") String dialect,
            @Value("${hibernate.hbm2dll}") String hbm2dll,
            @Value("${hibernate.showSql}") boolean showSql
    ) {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", dialect);
        properties.put("hibernate.hbm2dll", hbm2dll);
        properties.put("hibernate.show_sql", showSql);
        return properties;
    }

    @Bean
    public SessionFactory sessionFactory(
            DataSource dataSource,
            @Value("${hibernate.packagesToScan}") String packagesToScan,
            Properties hibernateProperties
    ) throws IOException {
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);
        sessionFactoryBean.setPackagesToScan(packagesToScan);
        sessionFactoryBean.setHibernateProperties(hibernateProperties);
        sessionFactoryBean.afterPropertiesSet();
        return sessionFactoryBean.getObject();
    }

    @Bean
    public PlatformTransactionManager transactionManager(SessionFactory sessionFactory) {
        return new HibernateTransactionManager(sessionFactory);
    }
}
