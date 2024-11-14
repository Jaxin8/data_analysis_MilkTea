package com.dark.api.config;

import com.dark.api.properties.OSSProperties;
import com.dark.api.utils.AliYunOSSUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AliYunOSSConfig {
    //创建AliYunOSSUtil对象交给容器来管理

    @Bean
    public AliYunOSSUtil createAliYunOSSUtil(OSSProperties ossProperties){
        AliYunOSSUtil aliYunOSSUtil = new AliYunOSSUtil(ossProperties.getEndpoint(),ossProperties.getAccessKeyId(),ossProperties.getAccessKeySecret(),ossProperties.getBucketName());
        return aliYunOSSUtil;
    }
}
