package com.kan.config;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import com.kan.controllers.UserController;
import com.kan.daoImpl.UserDaoImpl;
import com.kan.serviceImpl.UserServiceImpl;
import com.kan.services.UserService;

@Configuration//opt
@EnableJpaRepositories
@Import(RepositoryRestMvcConfiguration.class)
@EnableAutoConfiguration//opt
// @PropertySource("application.properties")

@PropertySource("classpath:application.properties")

@EntityScan("com.kan.entity")
@ComponentScan(basePackageClasses = { UserController.class, UserService.class, UserServiceImpl.class,
		UserDaoImpl.class })//opt

public class Application extends SpringBootServletInitializer {

	public static void main(String[] args) throws IOException {
		// new File(UploadingController.uploadingdir).mkdirs();
		SpringApplication.run(Application.class, args);
	}
	
	

//	@Bean
//	EmbeddedServletContainerCustomizer containerCustomizer() throws Exception {
//		return (ConfigurableEmbeddedServletContainer container) -> {
//			if (container instanceof TomcatEmbeddedServletContainerFactory) {
//				TomcatEmbeddedServletContainerFactory tomcat = (TomcatEmbeddedServletContainerFactory) container;
//				tomcat.addConnectorCustomizers((connector) -> {
//					connector.setMaxPostSize(100000000); // 10 MB
//				});
//			}
//		};
//	}
	
//	@Bean(name = "multipartResolver")
//    public CommonsMultipartResolver getCommonsMultipartResolver() {
//        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
//        multipartResolver.setMaxUploadSize(20848820);   
//        multipartResolver.setMaxInMemorySize(418018841); 
//        return multipartResolver;
//    }
}
