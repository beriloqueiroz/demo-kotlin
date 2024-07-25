package com.beriloqueiroz.demo_kotlin.repository;

import com.beriloqueiroz.demo_kotlin.entity.PackageEntity
import org.springframework.data.jpa.repository.JpaRepository

interface PackageRepository: JpaRepository<PackageEntity, Long>
