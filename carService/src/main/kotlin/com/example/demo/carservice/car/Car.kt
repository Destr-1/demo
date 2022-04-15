package com.example.demo.carservice.car

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table


@Table(name = "cars")
@Entity
data class Car(
    @Id
    @Column(name = "car_id")
    var id: Int?=null,
    @Column(name = "name")
    var name: String?=null,
    @Column(name = "brand")
    var brand: String?=null,
    @Column(name = "carbody")
    var carBody: String?=null,
    @Column(name = "petrol100")
    var petrol100: Double=0.0
)

@Table(name = "dictrustoeng")
@Entity
data class Dict(
    @Id
    @Column(name = "namerus")
    var nameRus: String?=null,
    @Column(name = "nameeng")
    var nameEng: String?=null
)
