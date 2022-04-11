package com.example.demo.Car

import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.exchange

@Service
class CarPriseClient(private val storage: Storage,
                     private val restTemplate: RestTemplate,
                     @Value("\${car.price.address}") private val carPriceAddress: String
                     )
{
    fun getCarsPrise():MutableMap<Int, Int> =
        restTemplate.exchange<MutableMap<Int, Int>>("$carPriceAddress$GET_CAR_PRICE", HttpMethod.GET).body.orEmpty().toMutableMap()


    //fun getCarsPrise() = storage.carsPrise
}

private const val GET_CAR_PRICE = "/cars/price"

