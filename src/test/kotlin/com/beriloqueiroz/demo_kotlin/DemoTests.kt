package com.beriloqueiroz.demo_kotlin

import com.beriloqueiroz.demo_kotlin.entity.PackageEntity
import com.beriloqueiroz.demo_kotlin.entity.PackageEvent
import com.beriloqueiroz.demo_kotlin.repository.PackageRepository
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.assertj.core.api.Assertions.assertThat
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.MySQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers

@SpringBootTest
@Testcontainers
class DemoTests {
    var pkgId:Int = 0;
    companion object {
        @Container
        @JvmStatic
        val db = MySQLContainer("mysql")


        @BeforeAll
        @JvmStatic
        fun startDBContainer() {
            db.start()
        }

        @AfterAll
        @JvmStatic
        fun stopDBContainer() {
            db.stop()
        }

        @DynamicPropertySource
        @JvmStatic
        fun registerDBContainer(registry: DynamicPropertyRegistry) {
            registry.add("spring.datasource.url", db::getJdbcUrl)
            registry.add("spring.datasource.username", db::getUsername)
            registry.add("spring.datasource.password", db::getPassword)
        }
    }

    @Autowired
    private lateinit var packageRepository: PackageRepository;

    @Test
    fun `save data works`() {
        var pkg1 = packageRepository.save(createPackageEntity("1"))
        this.pkgId++
        pkg1.trackingEvents.add(createPackageEvent("1", pkg1))
        pkg1.trackingEvents.add(createPackageEvent("2", pkg1))
        pkg1 = packageRepository.save(pkg1)

        var dbPkg = packageRepository.findById(pkg1.packageId!!)
        assertThat(dbPkg.get().toString()).isEqualTo(pkg1.toString());

        var pkg2 = packageRepository.save(createPackageEntity("2"))
        this.pkgId++
        pkg2.trackingEvents.add(createPackageEvent("3", pkg2))
        pkg2.trackingEvents.add(createPackageEvent("4", pkg2))
        pkg2.trackingEvents.add(createPackageEvent("5", pkg2))
        pkg2 = packageRepository.save(pkg2)

        dbPkg = packageRepository.findById(pkg2.packageId!!)
        assertThat(dbPkg.get().toString()).isEqualTo(pkg2.toString());

        val pkgs = packageRepository.findAll();
        assertThat(pkgs.size).isEqualTo(this.pkgId+1);
        assertThat(pkgs.find { pk-> pk.packageId!!.equals(pkg1.packageId) }!!.trackingEvents.size).isEqualTo(2);
        assertThat(pkgs.find { pk-> pk.packageId!!.equals(pkg2.packageId) }!!.trackingEvents.size).isEqualTo(3);
    }

    @Test
    fun `findById works`() {
        val pkg1 = packageRepository.save(createPackageEntity("3"))
        this.pkgId++
        val dbPkg = packageRepository.findById(pkg1.packageId!!)
        assertThat(dbPkg.get().toString()).isEqualTo(pkg1.toString())
    }

    fun createPackageEntity(ref:String): PackageEntity {
        return PackageEntity(null, "2000-01-0"+ref, "2000-01-0"+ref, "CONFIRMED", arrayListOf());
    }

    fun createPackageEvent(ref:String, packageEntity: PackageEntity): PackageEvent {
        return PackageEvent(null, "Type", "AAA"+ref, "description"+ref, "Aqui", "Aculá", packageEntity)
    }
}