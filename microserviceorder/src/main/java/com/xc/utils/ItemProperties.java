package com.xc.utils;

//在SpringBoot中使用@ConfigurationProperties注解可以非常简单的将配置文件中的值映射成对象。

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@NoArgsConstructor
@Data
public class ItemProperties {
    private String url;

}