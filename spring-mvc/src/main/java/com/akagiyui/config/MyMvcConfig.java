package com.akagiyui.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

/**
 * @author AkagiYui
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.akagiyui.demo")
public class MyMvcConfig implements WebMvcConfigurer {
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
	}

	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		String prefix = "/WEB-INF/views/";
		String suffix = ".jsp";

		registry.jsp(prefix, suffix);
	}
}
