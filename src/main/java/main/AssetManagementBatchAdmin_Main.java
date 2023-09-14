package main;

import java.util.concurrent.Executor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@SpringBootApplication
@EnableScheduling
@EntityScan(basePackages ={"asset_main_schd_create_details","asset_main_schd_doservice_details","asset_schd_details","asset_main_schd_init_details","common"})
@EnableJpaRepositories(basePackages = {"asset_main_schd_create_details","asset_main_schd_doservice_details","asset_schd_details","asset_main_schd_init_details","common"})
@ComponentScan({"asset_main_schd_create_details","asset_main_schd_doservice_details","asset_schd_details","asset_main_schd_init_details","common"})
public class AssetManagementBatchAdmin_Main extends SpringBootServletInitializer  
{
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(AssetManagementBatchAdmin_Main.class);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SpringApplication.run(AssetManagementBatchAdmin_Main.class, args);
	}
	
	@Bean(name = "asyncExecutor")
	public Executor taskExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(50);
		executor.setMaxPoolSize(100);
		executor.setQueueCapacity(500);
		executor.setThreadNamePrefix("prodservmods");
		executor.initialize();
		return executor;
	}
}