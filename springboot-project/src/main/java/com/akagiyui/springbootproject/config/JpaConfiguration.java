package com.akagiyui.springbootproject.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * JPA 配置类
 * @author AkagiYui
 */
@Configuration
// 启用自动填充
@EnableJpaAuditing
public class JpaConfiguration {
}
