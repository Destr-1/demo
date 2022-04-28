package com.example.demo.carservice.db

import com.example.demo.carservice.car.Car
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest
import org.springframework.jdbc.core.JdbcTemplate
import javax.sql.DataSource
import kotlin.test.assertEquals

@JdbcTest
class JdbcCarRepositoryTest {
    @Autowired
    private lateinit var ds: DataSource

    private lateinit var result: JdbcCarRepositoryImpl

    @BeforeEach
    fun startup() {
        result = JdbcCarRepositoryImpl(JdbcTemplate(ds))
    }

    @Test
    fun testGetCars() {
        assertEquals(5, result.getCars().size)
        assertEquals(cars, result.getCars())
        var result1 = JdbcCarRepositoryImpl(JdbcTemplate(ds)).getCar(1)
        assertEquals(cars[0], result1)
    }

    @Test
    fun testGetDict(){
        assertEquals(dictRusToEng, result.getDictRusToEng())
    }

    @Test
    fun testSaveCar(){
        val car = Car(6,"Нива", "Лада", "универсал", 14.0)
        result.saveCar(car)
        assertEquals(6, result.getCars().size)
        assertEquals(car, result.getCar(6))
    }

}

