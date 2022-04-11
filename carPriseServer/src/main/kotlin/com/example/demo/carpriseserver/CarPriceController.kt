package com.example.demo.carpriseserver

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("cars")
class CarPriceController(private val carPriceClient:CarPriceClnt) {
    @GetMapping("/price")
    fun carPrice() = carPriceClient.getCarPrice()
}