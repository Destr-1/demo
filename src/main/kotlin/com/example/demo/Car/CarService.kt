package com.example.demo.Car

import org.springframework.stereotype.Service

@Service
class CarService(val carClient: CarClient) {
    fun carsList(): Map<Int, Car> = carClient.getCarsList()

    fun getCar(id: Int) = carClient.getCar(id)


    fun carAdd(name: String, brand: String, carbody: String, petrol100: Double, prise: Int) = carClient.addCar(Car(name, brand, carbody, petrol100), prise)


    fun getPriseList(): MutableMap<Car, Int?> {
        //carsList().keys.associateWith { carClient.getCarsPrise().values }
        var carsPrise: MutableMap<Car, Int?> = mutableMapOf()
        for (car in carsList()) {
            carsPrise.set(car.value, carClient.getCarsPrise()[car.key])
        }
        return carsPrise
    }
    fun listCars(): List<Car> = carsList().values.toList()
    val exchangeRate = 0.01
//    var dictRusToEng: MutableMap<String, String> = carClient.getDict()

    val dictRusToEng: MutableMap<String, String> by lazy { carClient.getDict() }


    fun translate(): List<Car> =
         carsList().map { changeCarRusToEng(it.value) }

    private fun changeCarRusToEng(car: Car): Car {
        // val newPrise: Int = (car.prise * exchangeRate).toInt()
        val newName = dictRusToEng.getOrDefault(car.name, car.name)
        val newBrand = dictRusToEng.getOrDefault(car.brand, car.brand)
        val newCarBody = dictRusToEng.getOrDefault(car.carBody, car.carBody)
        return Car(newName, newBrand, newCarBody, car.petrol100)
    }

    fun search(name:String?, brand: String?, carbody: String?, offset: Int, limit: Int) = carClient.search(name, brand, carbody, offset, limit)

    fun carGroup(list: Collection<Car>): Map<String, List<Car>> = list.groupBy { it.carBody }

    //fun predik(cars: List<Car>, f: (car: Car) -> Boolean) = translateAndSort(cars.filter(f)).take(3)

/*        fun translateAndSortSeq(list: Collection<Car>): List<Car> =
            list.asSequence().map { changeCarRusToEng(it) }.sortedBy { it.prise }.toList()

        fun carGroupSeq(list: Collection<Car>): Map<String, List<Car>> =
            list.asSequence().groupBy { it.carBody }.toMap()

        fun predikSeq(cars: List<Car>, f: (car: Car) -> Boolean) = translateAndSortSeq(cars.filter(f)).take(3)*/

}
