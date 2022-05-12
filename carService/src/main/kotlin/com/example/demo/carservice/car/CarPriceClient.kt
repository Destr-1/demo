package com.example.demo.carservice.car

import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpEntity
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.web.reactive.function.client.*
import reactor.core.publisher.Mono

@Service
class CarPriceClient(
    private val webClient: WebClient,
    @Value("\${car.price.address}") private val carPriceAddress: String
) {
    suspend fun getCarsPrise() = webClient
        .get()
        .uri("$carPriceAddress$GET_CAR_PRICE")
        .accept(MediaType.APPLICATION_JSON)
        .retrieve()
        .awaitBody<MutableMap<String, Int>>()


    fun setCarsPrice(id: Int, price: Int) {
        val price = Price(id, price)
        webClient
            .post()
            .uri("$carPriceAddress$SET_CAR_PRICE")
            .body(Mono.just(price), price::class.java)
            .retrieve()
            .bodyToMono(price::class.java)
            .subscribe()
    }
}

private const val USERS_URL_TEMPLATE = "/users/{"

private const val GET_CAR_PRICE = "/cars/price"
private const val SET_CAR_PRICE = "/cars/set-price"

