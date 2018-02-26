package org.xxz.myblog.common.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import org.springframework.web.servlet.view.velocity.VelocityConfig;
import org.springframework.web.servlet.view.velocity.VelocityConfigurer;
import org.springframework.web.servlet.view.velocity.VelocityViewResolver;
import org.xxz.myblog.common.constant.SystemConst;
import org.xxz.myblog.common.helper.SharedRenderVariableInterceptor;
import org.xxz.myblog.common.security.SecurityInterceptor;
import org.xxz.myblog.component.LocalCache;

import javax.validation.Validator;
import java.util.Properties;

/**
 * @author tt
 */
@Slf4j
@SuppressWarnings("deprecation")
@Configuration
public class WebConfiguration extends WebMvcConfigurerAdapter {
    
    @Value("${spring.velocity.charset:utf-8}")
    private String charset;

    @Bean
    @ConfigurationProperties(prefix = "spring.velocity")
    public VelocityConfig velocityConfig() {
        VelocityConfigurer configurer = new VelocityConfigurer();
        Properties velocityProperties = new Properties();
        velocityProperties.put("input.encoding", charset);
        velocityProperties.put("output.encoding", charset);
        configurer.setVelocityProperties(velocityProperties);
        return configurer;
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.velocity")
    public VelocityViewResolver viewResolver() {
        return new VelocityViewResolver();
    }
    
    @Bean
    public Validator validator() {
        // 默认配置文件ValidationMessages.properties
        return new LocalValidatorFactoryBean();
    }
    
    @Bean
    public MappingJackson2JsonView mappingJackson2JsonView() {
        MappingJackson2JsonView view = new MappingJackson2JsonView();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        view.setObjectMapper(objectMapper);
        return view;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        super.addInterceptors(registry);
        registry.addInterceptor(new SecurityInterceptor()).addPathPatterns("/**");
        registry.addInterceptor(new SharedRenderVariableInterceptor()).addPathPatterns("/**");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 特别注意 在win环境下 路径最后一定要加"/"
        String uploadUrl = LocalCache.getValue("upload_url");
        if (uploadUrl != null && !uploadUrl.endsWith("/")) {
            uploadUrl += "/";
        }
        log.info("====>uploadUrl:{}", uploadUrl);
        registry.addResourceHandler(SystemConst.STATIC_PREFIX + "/**").addResourceLocations("file:" + uploadUrl);
    }

    
}
