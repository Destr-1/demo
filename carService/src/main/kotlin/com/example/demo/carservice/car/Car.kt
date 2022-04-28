package com.example.demo.carservice.car

import javax.persistence.*


@Table(name = "cars")
@Entity
@SequenceGenerator(allocationSize = 1, name = "user_seq", sequenceName = "user_seq")
data class Car(
    @Id
    @GeneratedValue(generator = "user_seq")
    @Column(name = "id")
    var id: Int? = 0,
    @Column(name = "name")
    var name: String? = null,
    @Column(name = "brand")
    var brand: String? = null,
    @Column(name = "carbody")
    var carBody: String? = null,
    @Column(name = "petrol100")
    var petrol100: Double? = null
)

@Table(name = "dictrustoeng")
@Entity
data class Dict(
    @Id
    @Column(name = "namerus")
    var nameRus: String? = null,
    @Column(name = "nameeng")
    var nameEng: String? = null
)
