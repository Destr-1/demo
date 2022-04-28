package com.example.demo.carservice


import com.example.demo.carservice.car.*
import com.example.demo.carservice.car.Car
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.ninjasquad.springmockk.MockkBean
import io.mockk.clearAllMocks
import io.mockk.every
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActionsDsl
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import kotlin.test.assertEquals


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureMockMvc
class CarServiceTests @Autowired constructor(private val mockMvc: MockMvc, private val objectMapper: ObjectMapper) {


    @MockkBean
    private lateinit var carPriseClient: CarPriceClient


    @BeforeEach
    fun startup() {
        every { carPriseClient.getCarsPrise() } returns carsPrice
        every{ carPriseClient.setCarsPrice(any(), any())} answers { carsPrice[firstArg<Int>().toInt()] = secondArg<Int>().toInt() }


    }

    @AfterEach
    fun finish() {
        clearAllMocks()
    }

    @Test
    fun `тест на получение списка машин`() {
        val carsList = getListCars()
        assertEquals(carsList, cars)

        for (id in 0 until cars.size) {
            val car = getCarId(id+1)
            assertEquals(car, cars[id], "failed with $id")
        }
    }

    @Test
    fun `тестирование перевода машин на английский`() {
        val carsListEng = getcarsEng()
        assertEquals(carsListEng, carsEng.values.toList())
    }


    @Test
    fun `тестирование функции добавления авто`() {
        val name = "Нива"
        val brand = "Лада"
        val carBody = "универсал"
        val petrol100 = 13.0
        val price = 700000
        val status = addCar(name, brand, carBody, petrol100, price)
        assertEquals("Successful", status)
    }



    private fun getPriceList(): List<Pair<Car, Int?>> =
        mockMvc.get("/cars/pricelist").readResponse()

    private fun getListCars(): List<Car> =
        mockMvc.get("/cars/listCars").readResponse()

    private fun getCarId(id: Int): Car =
        mockMvc.get("/cars/{id}", id).readResponse()

    private fun getcarsEng(): List<Car> =
        mockMvc.get("/cars/translate").readResponse()


    private fun addCar(name: String, brand: String, carBody: String, petrol100: Double, price: Int): String =
        mockMvc.perform(
            post("/cars/addCar").param("price", price.toString()).contentType(APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(Car(0, name, brand, carBody, petrol100)))
        ).andExpect(status().isOk).andReturn().response.contentAsString

    private inline fun <reified T> ResultActionsDsl.readResponse(expectedStatus: HttpStatus = HttpStatus.OK): T = this
        .andExpect { status { isEqualTo(expectedStatus.value()) } }
        .andReturn().response.getContentAsString(Charsets.UTF_8)
        .let { if (T::class == String::class) it as T else objectMapper.readValue(it) }


    val cars = mutableListOf(
        Car(1, "Камри", "Тойота", "седан", 131.5),
        Car(2, "Королла", "Тойота", "хэтчбек", 8.3),
        Car(3, "Тиида", "Ниссан", "седан", 10.0),
        Car(4, "Ларгус", "Лада", "универсал", 9.8),
        Car(5, "525", "БМВ", "седан", 15.5)
    )

    val carsPrice = mutableMapOf(
        1 to 2500000,
        2 to 1500000,
        3 to 1800000,
        4 to 1000000,
        5 to 4500000
    )

    val dictRusToEng = mutableMapOf(
        "Тойота" to "Toyota",
        "Ниссан" to "Nissan",
        "БМВ" to "BMW",
        "Лада" to "LADA",
        "седан" to "sedan",
        "хэтчбек" to "hatchback",
        "универсал" to "universal",
        "Камри" to "Camry",
        "Королла" to "Corolla",
        "Ларгус" to "Largus",
        "Тиида" to "Tiida",
        "525" to "525"
    )

    val carsEng = mutableMapOf(
        0 to Car(1, "Camry", "Toyota", "sedan", 131.5),
        1 to Car(2, "Corolla", "Toyota", "hatchback", 8.3),
        2 to Car(3, "Tiida", "Nissan", "sedan", 10.0),
        3 to Car(4, "Largus", "LADA", "universal", 9.8),
        4 to Car(5, "525", "BMW", "sedan", 15.5)
    )


}


