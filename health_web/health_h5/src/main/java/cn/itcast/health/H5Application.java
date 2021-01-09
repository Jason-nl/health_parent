package cn.itcast.health;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * created by Ethan on 2021/1/9 1:51 下午
 */
@SpringBootApplication
public class H5Application {
    public static void main(String[] args) {
        SpringApplication.run(H5Application.class, args);
        System.out.println("...h5服务启动...");
    }
}
