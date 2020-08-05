package com.anava.spring;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import net.minidev.json.JSONObject;
import org.apache.tomcat.util.json.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@SpringBootApplication
public class Application {

	private static final Logger log =  LoggerFactory.getLogger(Application.class);
	Map map;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	@Bean
	public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
		return args -> {
			Object object = restTemplate.getForObject(
					"http://localhost:8080/users/", Object.class);
			assert object != null;
			//log.info(object.toString());
		};
	}
}
