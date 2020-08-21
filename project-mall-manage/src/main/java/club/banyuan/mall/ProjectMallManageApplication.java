package club.banyuan.mall;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("club.banyuan.mall.mapper")
public class ProjectMallManageApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectMallManageApplication.class, args);
	}

}
