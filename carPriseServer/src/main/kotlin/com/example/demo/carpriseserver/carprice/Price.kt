package com.example.demo.carpriseserver.carprice

import java.io.Serializable
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Table(name = "carsprice")
@Entity
data class Price(
    @Id
    @Column(name = "car_id")
    val id: Int? = null,
    @Column(name = "price")
    val price: Int? = null
) : Serializable
