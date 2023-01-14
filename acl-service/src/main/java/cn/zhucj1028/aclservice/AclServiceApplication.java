package cn.zhucj1028.aclservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("cn.zhucj1028")
@MapperScan("cn.zhucj1028.aclservice.mapper")
public class AclServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AclServiceApplication.class, args);
    }

}
