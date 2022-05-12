package com.example.demo.carpriseserver.db

import com.example.demo.carpriseserver.carprice.Price

interface CarPriceRepository {
    fun getCarPrice(): MutableMap<Int, Int>

    fun setCarPrice(price: Price)

}