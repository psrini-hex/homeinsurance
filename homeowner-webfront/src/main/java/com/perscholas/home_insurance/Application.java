package com.perscholas.home_insurance;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import com.perscholas.first_servlet.index;

@EnableAutoConfiguration
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class, scanBasePackages = "com.perscholas")
public class Application extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(Application.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Autowired
    AutowireCapableBeanFactory beanFactory;

    @Bean
    ServletRegistrationBean myServletRegistration() {
        ServletRegistrationBean srb = new ServletRegistrationBean();
        final index servlet = new index();
        beanFactory.autowireBean(servlet);  // <--- The most important part
        srb.setServlet(servlet);
        srb.setUrlMappings(Arrays.asList("/"));	//, "/index", "/login"
        srb.setLoadOnStartup(1);
        return srb;
    }
}
