package it.ipfsdownloadmonitor.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ComponentScan(basePackages = "it.inera.monitweb.config")
public class MvcConfig implements WebMvcConfigurer {
	
    
}