package com.example.demo.carservice.car

import com.example.demo.carservice.db.CarRepository
import org.springframework.stereotype.Service

@Service
class CarClient(private val repository: CarRepository) {
    fun getCarsList() = repository.getCars()
    fun getCar(id: Int) = repository.getCar(id)

//    fun search(id: Int?, name: String?, brand: String?, carBody: String?, offset: Int, limit: Int) =
//        getCarsList().asSequence()
//            .filter { id == null || it.id == id }
//            .filter { brand == null || it.brand == brand }
//            .filter { name == null || it.name == name }.filter { carBody == null || it.carBody == carBody }.drop(offset)
//            .take(limit).toList()

    fun search(id: Int?, name: String?, brand: String?, carBody: String?, offset: Int, limit: Int) =
        repository.search(id, name, brand, carBody, offset, limit)

    fun addCar(car: Car): String {
        repository.saveCar(car);
        return "Successful";
    }
    fun getDict() = repository.getDictRusToEng()
}


