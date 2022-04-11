package com.example.demo.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.jdbc.core.JdbcTemplate
import javax.sql.CommonDataSource
import javax.sql.DataSource

@Configuration
@EnableJpaRepositories(basePackages = ["com.example.demo"])
class JdbcConfiguration {

    @Bean
    fun jdbcTemplate(dataSource: DataSource):JdbcTemplate = JdbcTemplate(dataSource)



}