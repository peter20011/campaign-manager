package org.example.backend.config;


import org.example.backend.models.EmeraldAccount;
import org.example.backend.repository.EmeraldAccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmeraldAccountConfig {
    @Bean
    CommandLineRunner commandLineRunner(EmeraldAccountRepository emeraldAccountRepository){
        return args -> {
            EmeraldAccount emeraldAccount = new EmeraldAccount();
            emeraldAccount.setBalance(0.0);
            emeraldAccountRepository.save(emeraldAccount);
        };
    }
}
