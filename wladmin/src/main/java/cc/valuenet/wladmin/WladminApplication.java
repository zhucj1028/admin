package cc.valuenet.wladmin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cc.value.wladmin.mapper")
public class WladminApplication {
    public static void main(String[] args) {
        SpringApplication.run(WladminApplication.class, args);
    }
}
