package com.example.crudmemoback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing // 로컬 데이터를 보여주기 위함!
public class CrudMemoBackApplication {

    public static void main(String[] args) {
        SpringApplication.run(CrudMemoBackApplication.class, args);
    }

}
