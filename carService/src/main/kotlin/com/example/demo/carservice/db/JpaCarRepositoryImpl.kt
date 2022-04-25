package com.example.demo.carservice.db

import com.example.demo.carservice.car.Car
import org.springframework.context.annotation.Primary
import org.springframework.data.domain.Example
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.util.Streamable
//import org.springframework.http.ContentDisposition.builder
//import org.springframework.http.codec.ServerSentEvent.builder
import org.springframework.stereotype.Service
import org.springframework.web.method.HandlerTypePredicate.builder
import java.util.stream.Stream.builder


@Primary
@Service
class JpaCarRepositoryImpl(private val repository: JpaCarRepo, private val repoDict: JpaDictRepo) : CarRepository {
    override fun getCars(): List<Car> {
//        return repository.findAll(Pageable.ofSize(2)).toList()
          return repository.findAll(PageRequest.of(0, 10 )).toList()
//        Pageable. unpaged()).toList()
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

    override fun search(id: Int?, name: String?, brand: String?, carBody: String?, offset: Int, limit: Int):List<Car> {

        var example: Car = Car(id, name, brand, carBody, 10.0)
//        if(name != null)
//            example.name=name
//        if(id != null)
//            example.id = id
//        if(brand != null)
//            example.brand=brand
//        if(carBody != null)
//            example.carBody=carBody

//        var example: Car = Car
//            .builder()
//            .id(id)
//            .name(name)
//            .brand(brand)
//            .carBody(carBody)
//            .build()

        return repository.findAll(Example.of(example))
//        return repository.findAllByNameAndBrand(name, brand)
    }
}
