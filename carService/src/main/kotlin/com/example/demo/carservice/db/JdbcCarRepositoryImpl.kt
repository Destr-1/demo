package com.example.demo.carservice.db

import com.example.demo.carservice.car.Car
import org.apache.tomcat.jni.User.username
import org.springframework.context.annotation.Primary
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.query
import org.springframework.jdbc.support.GeneratedKeyHolder
import org.springframework.jdbc.support.KeyHolder
import org.springframework.stereotype.Service
import java.sql.Connection
import java.sql.ResultSet


@Primary
@Service
class JdbcCarRepositoryImpl(private val jdbcTemplate: JdbcTemplate) : CarRepository {
    override fun getCars(): List<Car> {
        return jdbcTemplate.query("select * from cars limit 15 offset 0") { rs, a ->
            rowToCar(rs, a)
        }
    }

    override fun search(id: Int?, name: String?, brand: String?, carBody: String?, offset: Int, limit: Int): List<Car> {
        var query = "select * from cars"
        var ph = false
        var parametry = mutableListOf<Any>()
        var condition = ""
        if(id != null) {
            condition = "id=?"
            parametry.add(id)
            ph = true
        }
        if(name != null) {
            if(ph)
                condition += " and "
            condition += "name=?"
            parametry.add(name)
            ph = true
        }
        if(brand != null) {
            if(ph)
                condition += " and "
            condition += "brand=?"
            parametry.add(brand)
            ph = true
        }
        if(carBody != null) {
            if(ph)
                condition += " and "
            condition += "carbody=?"
            parametry.add(carBody)
            ph = true
        }
        if(ph){
            query = query + " where " + condition
        }
        query += " limit ? offset ?"
        parametry.add(limit)
        parametry.add(offset)
        return jdbcTemplate.query(query, *parametry.toTypedArray()){rs, a ->
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
        val INSERT_MESSAGE_SQL = "insert into CARS(car_id, name,brand,carbody, petrol100) values (next value for USER_SEQ,?,?,?,?)"
        val keyHolder : KeyHolder = GeneratedKeyHolder()

        jdbcTemplate.update({ connection: Connection ->
            val ps = connection
                .prepareStatement(INSERT_MESSAGE_SQL)
            ps.setString(1, car.name)
            ps.setString(2, car.brand)
            ps.setString(3, car.carBody)
            ps.setDouble(4, car.petrol100!!)
            ps
        }, keyHolder)
        print(keyHolder.key)


//        jdbcTemplate.update(
//            "insert into CARS(car_id, name,brand,carbody, petrol100) values (?,?,?,?,?)",
//            car.id,
//            car.name,
//            car.brand,
//            car.carBody,
//            car.petrol100,
//        )
    }

    override fun getDictRusToEng(): MutableMap<String, String> {
        val dict = jdbcTemplate.query("select * from dictRusToEng") { rs, _ ->
            Pair<String, String>(rs.getString("nameRus"), rs.getString("nameEng"))
        }
        return dict.toMap().toMutableMap()
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