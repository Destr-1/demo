package com.example.demo.carservice.car


import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/cars")
class CarsController(private val carService: CarService) {

    @GetMapping("/listCars")
    fun listCars() = carService.carsList()

    @GetMapping("{id}")
    fun getCar(@PathVariable id: Int) = carService.getCar(id)

    @GetMapping("/search")
    fun search(
        @RequestParam(required = false) id:Int?,
        @RequestParam(required = false) name: String?,
        @RequestParam(required = false) brand: String?,
        @RequestParam(required = false) carBody: String?,
        @RequestParam offset: Int,
        @RequestParam limit: Int
    ) =
        carService.search(id, name, brand, carBody, offset, limit)

    @GetMapping("/translate")
    fun translate() = carService.translate()

    @GetMapping("/pricelist")
    fun getPriceList() = carService.getPriceList()

    @PostMapping("/addCar")
    fun carAdd(
        @RequestBody car: Car,
        @RequestParam price: Int

    ) = carService.carAdd( car.name.orEmpty(), car.brand.orEmpty(), car.carBody.orEmpty(), car.petrol100, price)
}




