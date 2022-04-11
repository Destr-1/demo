package com.example.demo.carpriseserver

import org.springframework.stereotype.Service

@Service
class CarPriceClnt {
    fun getCarPrice() = carsPrise
    var carsPrise = mutableMapOf(
        0 to 92500000,
        1 to 91500000,
        2 to 91800000,
        3 to 91000000,
        4 to 94500000
    )
}