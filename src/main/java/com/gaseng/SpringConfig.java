package com.gaseng;

import com.gaseng.checklist.Repository.ChecklistRepository;
import com.gaseng.checklist.service.ChecklistService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;


@Configuration
public class SpringConfig {
    private final DataSource dataSource;
    private final EntityManager em;

    public SpringConfig(DataSource dataSource, EntityManager em) {
        this.dataSource = dataSource;
        this.em = em;
    }
    @Bean
    public ChecklistService checklistService() {
        return new ChecklistService(checklistRepository());
    }

    @Bean
    public ChecklistRepository checklistRepository() {
        return  new ChecklistRepository(em);
    }



}
