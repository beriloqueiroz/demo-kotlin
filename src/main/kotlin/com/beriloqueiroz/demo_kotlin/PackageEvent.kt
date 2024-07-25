package com.example.demo_kotlin;

import jakarta.persistence.*;

@Entity
@Table(name = "package_events")
class PackageEvent(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val eventId: Long?,

    @Column(nullable = false)
    val trackerType: String,

    @Column(nullable = false)
    val trackingCode: String,

    @Column(nullable = false)
    val description: String,

    @Column(nullable = false)
    val origin: String,

    @Column(nullable = false)
    val destination: String,

    @Column(nullable = false)
    val packageId: Long
)