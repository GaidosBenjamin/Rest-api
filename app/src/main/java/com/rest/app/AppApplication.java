package com.rest.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@ComponentScan({"com.rest"})
@SpringBootApplication(scanBasePackages = "com.rest")
@EnableJpaRepositories({
        "com.rest.impl.dao.repository",
        "com.rest.impl.dao.post.repository",
        "com.rest.impl.dao.address.repository",
        "com.rest.impl.dao.group.repository"
})
@EntityScan({
        "com.rest.impl.dao",
        "com.rest.impl.dao.post",
        "com.rest.impl.dao.address",
        "com.rest.impl.dao.group"
})
public class AppApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppApplication.class, args);
    }

}
