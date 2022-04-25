package com.example.demo.carservice.db

import com.example.demo.carservice.car.Car
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.data.util.Streamable

interface JpaCarRepo: JpaRepository<Car, Int>


