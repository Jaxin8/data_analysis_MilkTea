package com.dark.api.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("gxust.jwt")
@Data
public class JWTProperties {
    private String secure;
    private int expire;

}
