package top.xuqingquan.easynews;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement//开启事务管理
@MapperScan("top.xuqingquan.easynews.dao")//与dao层的@Mapper二选一写上即可(主要作用是扫包)
public class EasyNewsApplication {

    public static void main(String[] args) {
        SpringApplication.run(EasyNewsApplication.class, args);
    }
}
