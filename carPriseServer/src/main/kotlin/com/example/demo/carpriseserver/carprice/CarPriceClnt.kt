package com.example.demo.carpriseserver.carprice

import com.example.demo.carpriseserver.db.CarPriceRepository
import org.springframework.stereotype.Service

@Service
class CarPriceClnt(private val repository: CarPriceRepository) {
    fun getCarPrice() = repository.getCarPrice()

    fun setCarPrice(id: Int, price: Int) {
        repository.setCarPrice(Price(id, price))
    }
}
