package com.example.demo.carservice.db

import com.example.demo.carservice.car.Car
import org.springframework.context.annotation.Primary
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.query
import org.springframework.jdbc.support.GeneratedKeyHolder
import org.springframework.jdbc.support.KeyHolder
import org.springframework.stereotype.Service
import java.sql.Connection
import java.sql.ResultSet
import java.sql.Statement.RETURN_GENERATED_KEYS


@Primary
@Service
class JdbcCarRepositoryImpl(private val jdbcTemplate: JdbcTemplate) : CarRepository {
    override fun getCars(): List<Car> {
        return jdbcTemplate.query("select * from cars") { rs, a ->
            rowToCar(rs, a)
        }
    }

    override fun search(id: Int?, name: String?, brand: String?, carBody: String?, offset: Int, limit: Int): List<Car> {
        var query = "select * from cars"
        var ph = false
        var parametry = mutableListOf<Any>()
        var condition = ""
        if (id != null) {
            condition = "id=?"
            parametry.add(id)
            ph = true
        }
        if (name != null) {
            if (ph)
                condition += " and "
            condition += "name=?"
            parametry.add(name)
            ph = true
        }
        if (brand != null) {
            if (ph)
                condition += " and "
            condition += "brand=?"
            parametry.add(brand)
            ph = true
        }
        if (carBody != null) {
            if (ph)
                condition += " and "
            condition += "carbody=?"
            parametry.add(carBody)
            ph = true
        }
        if (ph) {
            query = query + " where " + condition
        }
        query += " limit ? offset ?"
        parametry.add(limit)
        parametry.add(offset)
        return jdbcTemplate.query(query, *parametry.toTypedArray()) { rs, a ->
            rowToCar(rs, a)
        }
    }

    override fun getCar(id: Int): Car {
        val result = jdbcTemplate.query("select * from cars where id = ?", id) { rs, a ->
            rowToCar(rs, a)
        }
        if (result.isEmpty()) {
            throw IllegalArgumentException("no data")
        }
        return result[0]
    }

    override fun saveCar(car: Car): Int {
        val keyHolder: KeyHolder = GeneratedKeyHolder()
        val INSERT_MESSAGE_SQL =
            "insert into CARS(id, name, brand, carbody, petrol100) values (NEXT VALUE FOR USER_SEQ,?,?,?,?)"
        jdbcTemplate.update({ connection: Connection ->
            val ps = connection
                .prepareStatement(INSERT_MESSAGE_SQL, RETURN_GENERATED_KEYS)

            ps.setString(1, car.name)
            ps.setString(2, car.brand)
            ps.setString(3, car.carBody)
            ps.setDouble(4, car.petrol100!!)
            ps
        }, keyHolder)
        return keyHolder.key?.toInt()!!
    }

    override fun getDictRusToEng(): MutableMap<String, String> {
        val dict = jdbcTemplate.query("select * from dictRusToEng") { rs, _ ->
            Pair<String, String>(rs.getString("nameRus"), rs.getString("nameEng"))
        }
        return dict.toMap().toMutableMap()
    }


    private fun rowToCar(rs: ResultSet, a: Int): Car =
        Car(
            rs.getInt("id"),
            rs.getString("name"),
            rs.getString("brand"),
            rs.getString("carbody"),
            rs.getDouble("petrol100")
        )
}
