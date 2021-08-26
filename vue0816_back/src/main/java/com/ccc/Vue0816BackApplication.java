package com.ccc;

import org.apache.ibatis.type.MappedJdbcTypes;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.ccc.dao")
public class Vue0816BackApplication {

	public static void main(String[] args) {
		SpringApplication.run(Vue0816BackApplication.class, args);
	}

}
