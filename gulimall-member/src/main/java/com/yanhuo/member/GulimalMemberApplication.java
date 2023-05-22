package com.yanhuo.member;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.yanhuo.member.dao")
@SpringBootApplication
public class GulimalMemberApplication {

	public static void main(String[] args) {
		SpringApplication.run(GulimalMemberApplication.class, args);
	}

}
