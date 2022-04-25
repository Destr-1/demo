package com.example.demo.carservice.db

import com.example.demo.carservice.car.Dict
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.PagingAndSortingRepository

interface JpaDictRepo : JpaRepository<Dict, String>

