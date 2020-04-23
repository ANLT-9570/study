package com.xc.config;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@ToString
@Component
@Data
@RefreshScope
public class JdbcConfigBean {
    @Value("${jdbc.url}")
    private String url;
    @Value("${jdbc.username}")
    private String username;
    @Value("${jdbc.password}")
    private String password;
    @Value("${jdbc.driver-class-name}")
    private String driverClassName; //driver-class-name
}
