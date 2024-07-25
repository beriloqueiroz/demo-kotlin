package com.beriloqueiroz.demo_kotlin

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class PackageController(private val service: PackageService) {

    @GetMapping
    fun get():String {
        return service.find();
    }
}