package cn.itcast.health;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * created by Ethan on 2021/1/9 1:39 下午
 */

@SpringBootApplication
@MapperScan("cn.itcast.health.mapper")
public class AppointmentApplication {
    public static void main(String[] args) {
        SpringApplication.run(AppointmentApplication.class, args);
        System.out.println("...预约服务启动...");
    }
}
