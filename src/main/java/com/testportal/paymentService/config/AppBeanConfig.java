package com.testportal.paymentService.config;

import java.util.Random;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppBeanConfig {

	@Bean
	Random radom() {
		return new Random(10000);
	}

//	@Bean
//	ObjectMapper mapper() {
//		return new ObjectMapper();
//	}

	@Bean
	RestTemplate rest() {
		return new RestTemplate();
	}

	@Bean
	ModelMapper mapper() {
		return new ModelMapper();
	}
}
