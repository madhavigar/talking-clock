package com.code.talkingclock.configuation;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/***
 * 
 * 
 * @author 91959
 *
 */

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer{
	
	public void addCordMappings(CorsRegistry registry) {
		registry.addMapping("/**")
		.allowedOrigins("*")
		.exposedHeaders("X-Content-Type-Options,X-Frame-Options","X-XSS-Protection","Referrer-Policy","Contenty-Securoty-Policy")
		.maxAge(3600);
	}
	
	

}
