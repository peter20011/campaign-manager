package org.example.backend.config;


import org.example.backend.models.EmeraldAccount;
import org.example.backend.repository.EmeraldAccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * Configuration class for initializing an {@link EmeraldAccount} in the
 * database when the application starts. It uses {@link CommandLineRunner} to
 * create a new account with a default balance of 0.0 and save it to the
 * {@link EmeraldAccountRepository}.
 */
@Configuration
public class EmeraldAccountConfig {
    /**
     * Bean that runs on application startup to create and save a default
     * {@link EmeraldAccount} with an initial balance of 0.0.
     *
     * @param emeraldAccountRepository the repository used to save the account
     * @return a {@link CommandLineRunner} that creates and saves the default account
     */
    @Bean
    CommandLineRunner commandLineRunner(EmeraldAccountRepository emeraldAccountRepository){
        return args -> {
            EmeraldAccount emeraldAccount = new EmeraldAccount();
            emeraldAccount.setBalance(0.0);
            emeraldAccountRepository.save(emeraldAccount);
        };
    }
}
