package com.beriloqueiroz.demo_kotlin

import com.example.demo_kotlin.PackageRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class PackageService {
    @Autowired
    lateinit var packageRepository: PackageRepository

    fun find(): String{
        return "Olá Mundo";
    }
}