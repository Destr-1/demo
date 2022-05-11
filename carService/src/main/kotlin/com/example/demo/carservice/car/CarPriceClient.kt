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
    private val storage: Storage,
    private val restTemplate: RestTemplate,
    private val webClient: WebClient,
    @Value("\${car.price.address}") private val carPriceAddress: String
) {
    suspend fun getCarsPrise():WebClient.ResponseSpec

//            Mono<out MutableMap<String, Int>>
    {
        var map: MutableMap<String, Int> = mutableMapOf()
//        restTemplate.exchange<MutableMap<Int, Int>>("$carPriceAddress$GET_CAR_PRICE", HttpMethod.GET).body.orEmpty().toMutableMap()

         val mono = webClient
            .get()
            .uri("$carPriceAddress$GET_CAR_PRICE")
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()

//             .awaitBody()
            //.bodyToMono(map::class.java)

//                .subscribe( map)
//                .block() ?: throw RuntimeException("dddd")
//        print(map)
//        mono.subscribe { value ->
//            println("lkkkk\n$value")
//            map = value
//        }
//        print("dfdf$map")
//        return map.mapKeys { it.key.toInt() }.toMutableMap()
        return mono
    }

    fun setCarsPrice(id: Int, price: Int) {
        val price = Price(id, price)
        val request = HttpEntity(price)
//        restTemplate.exchange("$carPriceAddress$SET_CAR_PRICE", HttpMethod.POST, request, price.javaClass)
         webClient
            .post()
            .uri("$carPriceAddress$SET_CAR_PRICE")
            .body(Mono.just(price), price::class.java)
            .retrieve()
            .bodyToMono(price::class.java)
            .subscribe()
        //print(price2.block())
    }
}

private const val USERS_URL_TEMPLATE = "/users/{"

private const val GET_CAR_PRICE = "/cars/price"
private const val SET_CAR_PRICE = "/cars/setprice"

