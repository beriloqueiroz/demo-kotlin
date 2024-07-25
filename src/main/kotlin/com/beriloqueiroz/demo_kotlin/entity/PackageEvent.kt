package com.beriloqueiroz.demo_kotlin.entity;

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

    @ManyToOne
    @JoinColumn(name="package_id", nullable = false)
    val packageEntity: PackageEntity
) {
    override fun toString(): String {
        return "PackageEvent(eventId=$eventId, trackerType='$trackerType', trackingCode='$trackingCode', description='$description', origin='$origin', destination='$destination'"
    }
}