package com.example.demo.Car

import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service

@Service
class Storage {
    var cars = mutableMapOf(
        0 to Car(0, "Камри", "Тойота", "седан", 13.5),
        1 to Car(1, "Королла", "Тойота", "хэтчбек", 8.3),
        2 to Car(2, "Тиида", "Ниссан", "седан", 10.0),
        3 to Car(3, "Ларгус", "Лада", "универсал", 9.8),
        4 to Car(4, "525", "БМВ", "седан", 15.5)
    )

    fun getCarsList() = cars

    fun addCar(car: Car, price: Int): String {
        val newId = cars.size
        car.id = newId
        cars[newId] = car
        carsPrice[newId] = price
        if (cars.size == newId + 1) return "Successful"
        else return "Not successful"
    }
//    fun getDictRusToEnglish() = dictRusToEng


    var carsPrice = mutableMapOf(
        0 to 2500000,
        1 to 1500000,
        2 to 1800000,
        3 to 1000000,
        4 to 4500000
    )

    var dictRusToEng = mutableMapOf(
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