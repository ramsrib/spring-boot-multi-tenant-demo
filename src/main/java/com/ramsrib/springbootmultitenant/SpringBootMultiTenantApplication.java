package com.ramsrib.springbootmultitenant;

import com.ramsrib.springbootmultitenant.model.User;
import com.ramsrib.springbootmultitenant.repository.UserRepository;
import com.ramsrib.springbootmultitenant.tenant.TenantIdentifierResolver;
import org.hibernate.MultiTenancyStrategy;
import org.hibernate.cfg.Environment;
import org.hibernate.dialect.PostgreSQL9Dialect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@SpringBootApplication
public class SpringBootMultiTenantApplication {

  @Autowired
  private DataSource dataSource;

  public static void main(String[] args) {
    SpringApplication.run(SpringBootMultiTenantApplication.class, args);
  }

  // this is an alternative for hibernate bug
  @Bean
  public EntityManagerFactory customEntityManagerFactory() {
    HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
    vendorAdapter.setGenerateDdl(false); // turn off with Discriminator strategy so far!
    LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
    factory.setJpaVendorAdapter(vendorAdapter);
    factory.setPackagesToScan(SpringBootMultiTenantApplication.class.getPackage().getName());
    factory.setDataSource(dataSource);
    factory.getJpaPropertyMap().put(Environment.DIALECT, PostgreSQL9Dialect.class.getName());
    factory.getJpaPropertyMap().put(Environment.MULTI_TENANT, MultiTenancyStrategy.DISCRIMINATOR);
    factory.getJpaPropertyMap().put(Environment.MULTI_TENANT_IDENTIFIER_RESOLVER, new TenantIdentifierResolver());
    factory.afterPropertiesSet();
//    return factory;
    return factory.getObject();
  }

//  @Bean
//  public LocalContainerEntityManagerFactoryBean customerEntityManagerFactory(EntityManagerFactoryBuilder builder) {
////    HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
////    vendorAdapter.setGenerateDdl(false);
//    Map<String, Object> propertyMap = new HashMap<>();
//    propertyMap.put(Environment.DIALECT, PostgreSQL9Dialect.class.getName());
//    propertyMap.put(Environment.MULTI_TENANT, MultiTenancyStrategy.DISCRIMINATOR);
//    propertyMap.put(Environment.MULTI_TENANT_IDENTIFIER_RESOLVER, new TenantIdentifierResolver());
//    return builder
//        .dataSource(dataSource)
//        .properties(propertyMap)
//        .packages(User.class)
//        .persistenceUnit("schema")
//        .build();
//  }


}
