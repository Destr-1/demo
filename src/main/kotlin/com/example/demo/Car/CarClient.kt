package com.example.demo.Car

import org.springframework.stereotype.Service

@Service
class CarClient(val storage: Storage) {
    //var cars = storage.cars
    fun getCarsList() = storage.getCarsList()
    fun getCar(id: Int) = storage.getCarsList()[id]

    fun search(id: Int, name: String?, brand: String?, carBody: String?, offset: Int, limit: Int) =
        storage.getCarsList().values.asSequence().filter { brand == null || it.brand == brand }
            .filter { name == null || it.name == name }.filter { carBody == null || it.carBody == carBody }.drop(offset)
            .take(limit).toList()

    fun addCar(car: Car, price: Int): String = storage.addCar(car, price)

   // fun getCarsPrise() = storage.carsPrise

    fun getDict() = storage.dictRusToEng


}