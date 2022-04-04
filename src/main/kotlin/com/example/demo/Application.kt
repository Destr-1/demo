package com.example.demo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
class Application

fun main(args: Array<String>) {
	runApplication<Application>(*args)
}

/*
@RestController
@RequestMapping("/mobileApi")
class HelloController {
	@RequestMapping(value = ["/helloWorld"], method = [(RequestMethod.GET)])
	fun getHelloWordMessage(): ResponseEntity<String> =
		ResponseEntity.ok("Hello World1")

	@RequestMapping(value = ["/helloWorld/{name}"], method = [(RequestMethod.GET)])
	fun getHelloWordMessageWithName(
		@PathVariable("name") name: String
	): ResponseEntity<Any> =
		if (name != "Cristian") {
			ResponseEntity.ok(
				HelloResponse(
					message = "Hello $name",
					name = name
				)
			)
		} else {
			ResponseEntity.badRequest().body("I am Cristian")
		}
}

data class HelloResponse(
	val message: String,
	val name: String
)*/
