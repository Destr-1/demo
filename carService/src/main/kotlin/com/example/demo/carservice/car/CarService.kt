package com.example.demo.carservice.car

import org.springframework.stereotype.Service

@Service
class CarService(val carClient: CarClient, val carPriseClient: CarPriseClient) {
    fun carsList(): Map<Int, Car> = carClient.getCarsList()

    fun getCar(id: Int) = carClient.getCar(id)


    fun carAdd(id:Int, name: String, brand: String, carbody: String, petrol100: Double, price: Int)
        = carClient.addCar(Car(id, name, brand, carbody, petrol100), price)
        //carPriseClient.putCarsPrise(id, price)
//    }


    fun getPriceList(): MutableList<Pair<Car, Int?>> {
        val carsPrice = mutableListOf<Pair<Car, Int?>>()
        for (car in carsList()) {
            carsPrice.add(car.value to carPriseClient.getCarsPrise()[car.key])
        }
        return carsPrice
    }
    fun listCars(): List<Car> = carsList().values.toList()
    val exchangeRate = 0.01

    val dictRusToEng: MutableMap<String, String> by lazy { carClient.getDict() }


    fun translate(): List<Car> =
         carsList().map { changeCarRusToEng(it.value) }

    private fun changeCarRusToEng(car: Car): Car {
        // val newPrise: Int = (car.prise * exchangeRate).toInt()

        val newName = dictRusToEng.getOrDefault(car.name, car.name)
        val newBrand = dictRusToEng.getOrDefault(car.brand, car.brand)
        val newCarBody = dictRusToEng.getOrDefault(car.carBody, car.carBody)
        return Car(car.id, newName, newBrand, newCarBody, car.petrol100)
    }

    fun search(id:Int, name:String?, brand: String?, carbody: String?, offset: Int, limit: Int) = carClient.search(id, name, brand, carbody, offset, limit)

    fun carGroup(list: Collection<Car>): Map<String, List<Car>> = list.groupBy { it.carBody }

    //fun predik(cars: List<Car>, f: (car: Car) -> Boolean) = translateAndSort(cars.filter(f)).take(3)

/*        fun translateAndSortSeq(list: Collection<Car>): List<Car> =
            list.asSequence().map { changeCarRusToEng(it) }.sortedBy { it.prise }.toList()

        fun carGroupSeq(list: Collection<Car>): Map<String, List<Car>> =
            list.asSequence().groupBy { it.carBody }.toMap()

        fun predikSeq(cars: List<Car>, f: (car: Car) -> Boolean) = translateAndSortSeq(cars.filter(f)).take(3)*/

}
