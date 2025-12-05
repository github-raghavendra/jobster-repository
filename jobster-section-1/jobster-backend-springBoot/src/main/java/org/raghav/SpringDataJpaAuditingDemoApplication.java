package org.raghav;

import org.raghav.audit.AuditorAwareImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SpringDataJpaAuditingDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringDataJpaAuditingDemoApplication.class, args);
	}

    @Bean
    AuditorAware<String> auditorAware(){
		return new AuditorAwareImpl();
	}

}
