package com.example.demo.carpriseserver.db

import com.example.demo.carpriseserver.carprice.Price
import org.springframework.data.repository.PagingAndSortingRepository

interface JpaCarPriceRepo : PagingAndSortingRepository<Price, Int>
