package com.example.demo.carpriseserver.db

import com.example.demo.carpriseserver.carprice.Price
import org.springframework.context.annotation.Primary
import org.springframework.stereotype.Service

@Primary
@Service
class JpaCarPriceRepositoryImpl(private val repository: JpaCarPriceRepo) : CarPriceRepository {

    override fun getCarPrice(): MutableMap<Int, Int> {
        val result: MutableMap<Int, Int> = mutableMapOf()
        val repo = repository.findAll()
        for (item in repo) {
            result[item.id!!.toInt()] = item.price!!.toInt()
        }
        return result
    }

    override fun setCarPrice(price: Price) {
        repository.save(price)
    }
}
