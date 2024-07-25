package com.beriloqueiroz.demo_kotlin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
@EnableJpaRepositories
class DemoKotlinApplication

fun main(args: Array<String>) {
	runApplication<DemoKotlinApplication>(*args)
}
