package com.example.demo.carservice.db

import com.example.demo.carservice.car.Car
import org.springframework.context.annotation.Primary
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.query
import org.springframework.stereotype.Service
import java.sql.ResultSet

//@Primary
@Service
class JdbcCarRepositoryImpl(private val jdbcTemplate: JdbcTemplate) : CarRepository {
    override fun getCars(): List<Car> {
        return jdbcTemplate.query("select * from cars limit 3 offset 0") { rs, a ->
            rowToCar(rs, a)
        }
    }

    override fun getCar(id: Int): Car {
        val result = jdbcTemplate.query("select * from cars where car_id = ?", id) { rs, a ->
            rowToCar(rs, a)
        }
        if (result.isEmpty()) {
            throw IllegalArgumentException("no data")
        }
        return result[0]
    }

    override fun saveCar(car: Car) {
        jdbcTemplate.update(
            "insert into CARS(car_id, name,brand,carbody, petrol100) values (?,?,?,?,?)",
            car.id,
            car.name,
            car.brand,
            car.carBody,
            car.petrol100
        )
    }

    override fun getDictRusToEng(): MutableMap<String, String> {
        val dict = jdbcTemplate.query("select * from dictRusToEng") { rs, _ ->
            Pair<String, String>(rs.getString("nameRus"), rs.getString("nameEng"))
        }
        return dict.toMap().toMutableMap()
    }

    override fun search(id: Int?, name: String?, brand: String?, carBody: String?, offset: Int, limit: Int): List<Car> {
        TODO("Not yet implemented")
    }

    private fun rowToCar(rs: ResultSet, a: Int): Car =
        Car(
            rs.getInt("car_id"),
            rs.getString("name"),
            rs.getString("brand"),
            rs.getString("carbody"),
            rs.getDouble("petrol100")
        )
}