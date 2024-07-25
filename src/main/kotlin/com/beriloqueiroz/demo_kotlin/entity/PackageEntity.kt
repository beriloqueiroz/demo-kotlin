package com.beriloqueiroz.demo_kotlin.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "packages")
class PackageEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val packageId: Long?,

    @Column(nullable = false)
    val createdAt: String,

    @Column(nullable = false)
    val updatedAt: String,

    @Column(nullable = false)
    val lastStatus: String,

    @OneToMany(mappedBy="packageEntity", fetch = FetchType.EAGER, cascade = [CascadeType.PERSIST, CascadeType.MERGE])
    val trackingEvents: MutableList<PackageEvent>
) {
    override fun toString(): String {
        return "PackageEntity(packageId=$packageId, createdAt='$createdAt', updatedAt='$updatedAt', lastStatus='$lastStatus', trackingEvents=$trackingEvents)"
    }
}
