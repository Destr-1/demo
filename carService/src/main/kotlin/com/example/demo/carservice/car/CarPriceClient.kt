package com.example.demo.carservice.car

import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.exchange
import org.springframework.web.client.postForObject

@Service
class CarPriceClient(private val storage: Storage,
                     private val restTemplate: RestTemplate,
                     @Value("\${car.price.address}") private val carPriceAddress: String
                     )
{
    fun getCarsPrise():MutableMap<Int, Int> =
        restTemplate.exchange<MutableMap<Int, Int>>("$carPriceAddress$GET_CAR_PRICE", HttpMethod.GET).body.orEmpty().toMutableMap()

    fun setCarsPrice(id:Int, price:Int){
        val price = Price(id, price)
        val request = HttpEntity(price)
        restTemplate.exchange("$carPriceAddress$SET_CAR_PRICE", HttpMethod.POST, request, price.javaClass)

    }
}

private const val GET_CAR_PRICE = "/cars/price"
private const val SET_CAR_PRICE = "/cars/setprice"

