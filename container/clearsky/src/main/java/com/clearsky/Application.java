package com.clearsky;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.clearsky.config.SwaggerConfig;
import com.clearsky.config.WebConfig;

@SpringBootApplication
@Import({ WebConfig.class, SwaggerConfig.class })
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
