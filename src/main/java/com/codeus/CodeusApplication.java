package com.codeus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing//JPA auditing 활성화
@SpringBootApplication
public class CodeusApplication {

	public static void main(String[] args) {
		SpringApplication.run(CodeusApplication.class, args);
	}

}
