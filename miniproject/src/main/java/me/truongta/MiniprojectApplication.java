package me.truongta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


import springfox.documentation.swagger2.annotations.EnableSwagger2;



@SpringBootApplication
@EnableSwagger2
public class MiniprojectApplication {
	

	public static void main(String[] args) {
		SpringApplication.run(MiniprojectApplication.class, args);
	}
	
  

}
