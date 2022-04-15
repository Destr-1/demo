package com.example.demo.carservice.db

import com.example.demo.carservice.car.Car
import org.springframework.data.repository.PagingAndSortingRepository

interface JpaCarRepo: PagingAndSortingRepository<Car, Int>

