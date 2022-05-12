package com.example.demo.carpriseserver.carprice

import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("cars")
class CarPriceController(private val carPriceClient: CarPriceClnt) {
    @GetMapping("/price")
    fun carPrice():MutableMap<Int, Int> {
        return carPriceClient.getCarPrice()
    }

    @PostMapping("/set-price")
    fun setCarPrice(
        @RequestBody price: Price
    ) {
        carPriceClient.setCarPrice(price.id ?: 0, price.price ?: 0)
    }
}