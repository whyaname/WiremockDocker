package com.anava.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.IntStream;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	Map map;

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
			map = (Map) object;
			for (Object obj : map.entrySet()) {
				if(obj instanceof Map.Entry) {
					if (((Map.Entry) obj).getValue() instanceof ArrayList) {
						IntStream.range(0, ((ArrayList) ((Map.Entry) obj).getValue()).size()).filter(i -> ((ArrayList)
								((Map.Entry) obj).getValue()).get(i) instanceof LinkedHashMap).forEach(i -> {
							for (Object entry : ((LinkedHashMap)
									((ArrayList) ((Map.Entry) obj).getValue()).get(i)).entrySet()) {
								if (entry instanceof Map.Entry) {
									//base level entries
								}
							}
						});
					}
				}
			}
		};
	}
}
