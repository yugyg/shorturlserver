package com.yugyg.shorturlserver;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "com.sun.*")
@MapperScan(value = "com.sun.*mapper")
@EnableAutoConfiguration
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class,HibernateJpaAutoConfiguration.class})
public class SpringBootApplicationStart implements CommandLineRunner
{
	private static Logger logger =LoggerFactory.getLogger(SpringBootApplicationStart.class);

	public static void main(String[] args) {
		  long begin = System.currentTimeMillis();
		  ApplicationContext ctx = SpringApplication.run(SpringBootApplicationStart.class, args);
		  long end = System.currentTimeMillis();
		  logger.info("============程序启动成功!      耗时间:{ }毫秒==============",end-begin);
	}
	@Override
	public void run(String... args) throws  Exception {
	  
	}
}
 