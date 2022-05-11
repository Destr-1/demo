package com.example.demo.carservice.car

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.springframework.cglib.proxy.Dispatcher
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.awaitBody

@Service
class CarService(val carClient: CarClient, val carPriceClient: CarPriceClient) {
    fun carsList() = carClient.getCarsList()

    fun getCar(id: Int) = carClient.getCar(id)


    fun carAdd(name: String, brand: String, carbody: String, petrol100: Double?, price: Int): String {
        CoroutineScope(Dispatchers.Default)
            .launch {
                val newId = carClient.addCar(Car(0, name, brand, carbody, petrol100)) //carClient.getCarsList().size + 1
                carPriceClient.setCarsPrice(newId, price)
            }
        return "Successful"
    }

    fun getPriceList(): MutableList<Pair<Car, Int?>> {
        val carsPriceList = mutableListOf<Pair<Car, Int?>>()
//        val carsPrices:MutableMap<Int, Int> = carPriceClient.getCarsPrise()
        runBlocking {
            launch {
                val carsPrices = carPriceClient.getCarsPrise()
//                    .toFuture()
//              TODO("Some long job")
                val prices = carsPrices.awaitBody<MutableMap<String, Int>>()
//                    .join()
                for (car in carsList()) {
                    carsPriceList.add(car to prices[car.id.toString()])
                }
            }
        }
        return carsPriceList
    }


    val dictRusToEng: MutableMap<String, String> by lazy { carClient.getDict() }


    fun translate(): List<Car> =
        carsList().map { changeCarRusToEng(it) }

    private fun changeCarRusToEng(car: Car): Car {
        val newName = dictRusToEng.getOrDefault(car.name, car.name)
        val newBrand = dictRusToEng.getOrDefault(car.brand, car.brand)
        val newCarBody = dictRusToEng.getOrDefault(car.carBody, car.carBody)
        return Car(car.id, newName, newBrand, newCarBody, car.petrol100)
    }

    fun search(id: Int?, name: String?, brand: String?, carbody: String?, offset: Int, limit: Int) =
        carClient.search(id, name, brand, carbody, offset, limit)

}
