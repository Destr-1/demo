package com.example.demo.carservice.car

import org.springframework.stereotype.Service

@Service
class CarService(val carClient: CarClient, val carPriceClient: CarPriceClient) {
    fun carsList() = carClient.getCarsList()

    fun getCar(id: Int) = carClient.getCar(id)


    fun carAdd(name: String, brand: String, carbody: String, petrol100: Double?, price: Int): String {
        val newId = carClient.getCarsList().size + 1
        carPriceClient.setCarsPrice(newId, price)
        return carClient.addCar(Car(newId, name, brand, carbody, petrol100))
    }

       fun getPriceList(): MutableList<Pair<Car, Int?>> {
           val carsPrice = mutableListOf<Pair<Car, Int?>>()
           for (car in carsList()) {
               carsPrice.add(car to carPriceClient.getCarsPrise()[car.id])
           }
           return carsPrice
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
