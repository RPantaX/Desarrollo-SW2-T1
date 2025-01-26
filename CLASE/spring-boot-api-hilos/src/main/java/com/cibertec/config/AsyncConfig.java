package com.cibertec.config;

import java.util.concurrent.Executor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync(proxyTargetClass = true) 
public class AsyncConfig {

	@Bean(name = "correoTaskExecutor")
    public Executor correoTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(3); 
        executor.setMaxPoolSize(3); 
        executor.setQueueCapacity(10); 
        executor.setThreadNamePrefix("CorreoHilos-");
        executor.initialize();
        return executor;
    }
	
}