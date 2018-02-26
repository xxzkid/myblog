package org.xxz.myblog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author tt
 */
@SpringBootApplication
@ComponentScan(basePackages = {"org.xxz.myblog"})
@ServletComponentScan(basePackages = {"org.xxz.myblog"})
public class Application {
    
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
