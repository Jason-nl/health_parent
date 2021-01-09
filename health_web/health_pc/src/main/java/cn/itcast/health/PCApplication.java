package cn.itcast.health;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * created by Ethan on 2021/1/9 1:44 下午
 */
@SpringBootApplication
public class PCApplication {
    public static void main(String[] args) {
        SpringApplication.run(PCApplication.class, args);
        System.out.println("...web服务启动...");
    }
}
