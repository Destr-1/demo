package com.example.demo.carservice.db

import com.example.demo.carservice.car.Car
import org.springframework.context.annotation.Primary
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Primary
@Service
class JpaCarRepositoryImpl(private val repository: JpaCarRepo, private val repoDict: JpaDictRepo) : CarRepository {
    override fun getCars(): List<Car> {
        return repository.findAll(Pageable.unpaged()).toList()
    }

    override fun getCar(id: Int): Car {
        val result = repository.findById(id)
        if(result.isEmpty)
            throw IllegalArgumentException("no data")
        return result.get()
    }

    override fun saveCar(car: Car) {
        repository.save(car)
    }

    override fun getDictRusToEng(): MutableMap<String, String> {
        val result: MutableMap<String, String> = mutableMapOf()
        val repo = repoDict.findAll()
        for (item in repo){
            result[item.nameRus.toString()] = item.nameEng.toString()
        }
        return result
    }
}
