package com.example.demo_kotlin;

import jakarta.persistence.*;

@Entity
@Table(name = "packages")
class PackageEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val packageId: Long?,

    @Column(nullable = false)
    val createdAt: String,

    @Column(nullable = false)
    val updatedAt: String,

    @Column(nullable = false)
    val lastStatus: String,

    @OneToMany
    @JoinColumn(name = "package_id", nullable = false)
    val trackingEvents: MutableList<PackageEvent>

)
