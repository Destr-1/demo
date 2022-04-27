package com.example.demo.carservice.db

import com.example.demo.carservice.car.Car
import org.springframework.context.annotation.Primary
import org.springframework.data.domain.*
import org.springframework.stereotype.Service



//@Primary
@Service
class JpaCarRepositoryImpl(private val repository: JpaCarRepo, private val repoDict: JpaDictRepo) : CarRepository {
    override fun getCars(): List<Car> {
          return repository.findAll(PageRequest.of(0, 10 )).toList()
    }


    override fun getCar(id: Int): Car {
        val result = repository.findById(id)
        if(result.isEmpty)
            throw IllegalArgumentException("no data")
        return result.get()
    }

    override fun saveCar(car: Car) {
        val savedCar = repository.save(car)
    }

    override fun getDictRusToEng(): MutableMap<String, String> {
        val result: MutableMap<String, String> = mutableMapOf()
        val repo = repoDict.findAll()
        for (item in repo){
            result[item.nameRus.toString()] = item.nameEng.toString()
        }
        return result
    }

    override fun search(id: Int?, name: String?, brand: String?, carBody: String?, offset: Int, limit: Int):List<Car> {
        var example = Car(id=id, name=name, brand = brand, carBody=carBody)
        val matcher = ExampleMatcher.matchingAll().withIgnoreCase()
        return repository.findAll(Example.of(example, matcher), PageRequest.of(offset, limit)).toList()
    }
}
