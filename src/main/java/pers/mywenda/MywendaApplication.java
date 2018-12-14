package pers.mywenda;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MywendaApplication {

    private static final Logger logger = LoggerFactory.getLogger(MywendaApplication.class);

    public static void main(String[] args) {
        logger.info("druid 启动啦");
        SpringApplication.run(MywendaApplication.class, args);
    }

}

