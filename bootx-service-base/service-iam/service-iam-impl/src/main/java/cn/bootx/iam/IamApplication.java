package cn.bootx.iam;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
* 身份识别与访问管理
* @author xxm  
* @date 2021/7/12 
*/
@Slf4j
@SpringBootApplication
@MapperScan(annotationClass = Mapper.class)
public class IamApplication {
    public static void main(String[] args) throws UnknownHostException {
        ConfigurableApplicationContext application = SpringApplication.run(IamApplication.class, args);

        Environment env = application.getEnvironment();
        log.info("\n----------------------------------------------------------\n\t" +
                        "应用 '{}' 运行成功! 访问连接:\n\t" +
                        "Swagger文档: \t\thttp://{}:{}{}{}/doc.html\n\t" +
                        "----------------------------------------------------------",
                env.getProperty("spring.application.name"),
                InetAddress.getLocalHost().getHostAddress(),
//                "localhost",
                env.getProperty("server.port"),
                env.getProperty("server.servlet.context-path", ""),
                env.getProperty("spring.mvc.servlet.path", "")
        );
    }
}
