package com.example.demo.carservice.db

import com.example.demo.carservice.car.Car

interface CarRepository {
    fun getCars(): List<Car>

    fun getCar(id: Int): Car

    fun saveCar(car: Car): Int

    fun getDictRusToEng(): MutableMap<String, String>

    fun search(id: Int?, name: String?, brand: String?, carBody: String?, offset: Int, limit: Int): List<Car>


}