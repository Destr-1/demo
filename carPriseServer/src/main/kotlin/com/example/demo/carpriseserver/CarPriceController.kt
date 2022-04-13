package com.example.demo.carpriseserver

import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("cars")
class CarPriceController(private val carPriceClient:CarPriceClnt) {
    @GetMapping("/price")
    fun carPrice() = carPriceClient.getCarPrice()

    @PostMapping("/setprice")
    fun setCarPrice(
        @RequestParam id : Int,
        @RequestParam price: Int
    ){
        carPriceClient.setCarPrice(id, price)
    }
}