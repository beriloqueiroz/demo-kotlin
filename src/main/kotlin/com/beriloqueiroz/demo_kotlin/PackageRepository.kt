package com.example.demo_kotlin;

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository;

@Repository
interface PackageRepository: JpaRepository<PackageEntity, Long> {
}
