package com.example.demo.carservice.db

import com.example.demo.carservice.car.Car
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import kotlin.test.assertEquals


@DataJpaTest
internal class JpaCarRepositoryTest {

    @Autowired
    private lateinit var carRepo: JpaCarRepo

    @Autowired
    private lateinit var dictRepo: JpaDictRepo

    @Test
    fun testGetCars(){
        var result = JpaCarRepositoryImpl(carRepo, dictRepo).getCars()
        assertEquals(5, result.size)
        assertEquals(cars, result)
        var result1 = JpaCarRepositoryImpl(carRepo, dictRepo).getCar(1)
        assertEquals(cars[0], result1)
    }

    @Test
    fun testGetDict(){
        var result = JpaCarRepositoryImpl(carRepo, dictRepo).getDictRusToEng()
        assertEquals(dictRusToEng, result)
    }

    @Test
    fun testSaveCar(){
        val car = Car(6,"Нива", "Лада", "универсал", 14.0)
        JpaCarRepositoryImpl(carRepo, dictRepo).saveCar(car)
        assertEquals(6, JpaCarRepositoryImpl(carRepo, dictRepo).getCars().size)
        assertEquals(car, JpaCarRepositoryImpl(carRepo, dictRepo).getCar(6))
    }
    @Test
    fun testSearch(){
        val car = JpaCarRepositoryImpl(carRepo, dictRepo).search(name="Камри", offset = 0, limit = 10, brand = null, carBody = null, id=null)[0]
        assertEquals(cars[0], car)
    }

}

val cars = listOf(
    Car(1, "Камри", "Тойота", "седан", 131.5),
    Car(2, "Королла", "Тойота", "хэтчбек", 8.3),
    Car(3, "Тиида", "Ниссан", "седан", 10.0),
    Car(4, "Ларгус", "Лада", "универсал", 9.8),
    Car(5, "525", "БМВ", "седан", 15.5)
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