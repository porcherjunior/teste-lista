package br.com.teste.contract.config;

import io.swagger.jaxrs.config.BeanConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public BeanConfig beanConfig() {
        BeanConfig beanConfig = new BeanConfig();
        beanConfig.setTitle("teste-lista api docs");
        beanConfig.setVersion("1.0.0");
        beanConfig.setBasePath("/demo");
        beanConfig.setResourcePackage("br.com.teste.contract");
        beanConfig.setScan(true);
        return beanConfig;
    }
}
