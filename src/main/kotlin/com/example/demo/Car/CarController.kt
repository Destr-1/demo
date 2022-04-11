package com.example.demo.Car

import io.swagger.v3.oas.annotations.parameters.RequestBody
import org.springframework.web.bind.annotation.*
import java.awt.print.Pageable

@RestController
@RequestMapping("/cars")
class CarsController(private val carService: CarService) {

    @GetMapping("/listCars")
    fun listCars() = carService.listCars()

    @GetMapping("{id}")
    fun getCar(@PathVariable id: Int) = carService.getCar(id)

    @GetMapping("/search")
    fun search(
        @RequestParam(required = false) name: String?,
        @RequestParam(required = false) brand: String?,
        @RequestParam(required = false) carBody: String?,
        @RequestParam offset: Int,
        @RequestParam limit: Int
    ) =
        carService.search(0, name, brand, carBody, offset, limit)

    @GetMapping("/translate")
    fun translate() = carService.translate()

    @GetMapping("/pricelist")
    fun getPriceList() = carService.getPriceList()

    @PostMapping("/addCar")
    fun carAdd(
        @RequestBody name: String,
        @RequestBody brand: String,
        @RequestBody carBody: String,
        @RequestBody petrol100: Double,
        @RequestBody prise: Int
    ) =
        carService.carAdd(0, name, brand, carBody, petrol100, prise)
}


/*@GetMapping("/listCars")
    fun translateAndSort(cars: Collection<Car>) = CarService.translateAndSortSeq(cars)*/

