package com.example.demo.carpriseserver.configuration

import com.example.demo.carpriseserver.db.CarPriceRepository
import com.example.demo.carpriseserver.db.JpaCarPriceRepo
import com.example.demo.carpriseserver.db.JpaCarPriceRepositoryImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.jdbc.core.JdbcTemplate
import javax.sql.DataSource

@Configuration
@EnableJpaRepositories(basePackages = ["com.example.demo.carpriseserver"])

class JpaConfiguration {
    @Bean
    fun carPriceRepository(jpaCarPriceRepo: JpaCarPriceRepo): CarPriceRepository =
        JpaCarPriceRepositoryImpl(jpaCarPriceRepo)
}