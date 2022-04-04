package com.example.demo

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.ninjasquad.springmockk.MockkBean
import com.example.demo.Car.Car
import com.example.demo.Car.CarClient
import com.example.demo.Car.CarService
import com.example.demo.Car.Storage
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.verify
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.junit.jupiter.api.BeforeEach
import org.springframework.http.HttpStatus
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActionsDsl
import org.springframework.test.web.servlet.get
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@SpringBootTest
@AutoConfigureMockMvc
class CarServiceTests() {

    @Test
    fun contextLoads() {
    }
    @MockkBean
    private lateinit var storage: Storage

    @BeforeEach
    fun startup() {

        every { storage.getCarsList() } returns cars
        every { storage.dictRusToEng } returns dictRusToEng
        every { storage.carsPrise } returns carsPrise
        every { storage.addCar(any(), any()) } returns "Successful"
    }

    @AfterEach
    fun finish() {
        clearAllMocks()
    }

    val cars = mutableMapOf(
        0 to Car("Камри", "Тойота", "седан", 13.5),
        1 to Car("Королла", "Тойота", "хэтчбек", 8.3),
        2 to Car("Тиида", "Ниссан", "седан", 10.0),
        3 to Car("Ларгус", "Лада", "универсал", 9.8),
        4 to Car("525", "БМВ", "седан", 15.5)
    )

    val carsPrise = mutableMapOf(
        0 to 2500000,
        1 to 1500000,
        2 to 1800000,
        3 to 1000000,
        4 to 4500000
    )

    val dictRusToEng = mutableMapOf(
        "Тойота" to "Toyota",
        "Ниссан" to "Nissan",
        "БМВ" to "BMW",
        "Лада" to "LADA",
        "седан" to "sedan",
        "хэтчбек" to "hatchback",
        "универсал" to "universal",
        "Камри" to "Camry",
        "Королла" to "Corolla",
        "Ларгус" to "Largus",
        "Тиида" to "Tiida",
        "525" to "525"
    )


}
