package cn.myesn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tk.mybatis.spring.annotation.MapperScan;

// 扫描 mybatis 通用 mapper 所在的包
@MapperScan(basePackages = "cn.myesn.mapper")
// 扫描所有包以及相关组件包
@ComponentScan(basePackages = {"cn.myesn", "org.n3r.idworker"})
//@EnableTransactionManagement // 可加可不加，因为  @SpringBootApplication 里的 @EnableAutoConfiguration 已经处理了（已经加了）
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
