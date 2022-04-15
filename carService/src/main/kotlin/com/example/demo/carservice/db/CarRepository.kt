package com.example.demo.carservice.db

import com.example.demo.carservice.car.Car

interface CarRepository {
    fun getCars(): List<Car>

    fun getCar(id: Int): Car

    fun saveCar(car: Car)

    fun getDictRusToEng():MutableMap<String, String>
}