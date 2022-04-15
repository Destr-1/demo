package com.example.demo.carservice.db

import com.example.demo.carservice.car.Dict
import org.springframework.data.repository.PagingAndSortingRepository

interface JpaDictRepo : PagingAndSortingRepository<Dict, String>

