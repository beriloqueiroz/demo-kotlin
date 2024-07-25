package com.beriloqueiroz.demo_kotlin

import com.beriloqueiroz.demo_kotlin.repository.PackageRepository
import org.springframework.stereotype.Service

@Service
class PackageService (private val packageRepository: PackageRepository) {
    fun find(): String{
        return "Olá Mundo";
    }
}