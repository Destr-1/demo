package com.example.demo.carservice.db

import com.example.demo.carservice.car.Dict
import org.springframework.data.jpa.repository.JpaRepository

interface JpaDictRepo : JpaRepository<Dict, String>

