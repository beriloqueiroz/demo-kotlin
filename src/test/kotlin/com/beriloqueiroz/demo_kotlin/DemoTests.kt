package com.beriloqueiroz.demo_kotlin

import com.example.demo_kotlin.PackageEntity
import com.example.demo_kotlin.PackageEvent
import com.example.demo_kotlin.PackageRepository
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.assertj.core.api.Assertions.assertThat
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.testcontainers.service.connection.ServiceConnection
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.MySQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers

@DataJpaTest
@Testcontainers
class DemoTests {

    companion object {
        @Container
        @ServiceConnection
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
        val pkg1 = packageRepository.save(createPackageEntity(1L))
        val pkg2 = packageRepository.save(createPackageEntity(2L))

        assertThat(pkg1).matches { it.packageId!!.equals(0L) && it.createdAt == "2000-01-01" }
        assertThat(pkg2).matches { it.packageId!!.equals(1L) && it.createdAt == "2000-01-02" }

        assertThat(packageRepository.findAll()).containsExactly(pkg1, pkg2)
    }

    @Test
    fun `findById works`() {
        val pkg1 = packageRepository.save(createPackageEntity(3L))
        assertThat(pkg1).matches { it.packageId!!.equals(3L) && it.createdAt == "2000-01-03" }
        val dbPkg = packageRepository.findById(pkg1.packageId!!)
        assertThat(dbPkg)
            .isNotNull
            .matches { it.get().packageId!!.equals(1L) && it.get().createdAt == "2000-01-02" }
    }

    fun createPackageEntity(id:Long): PackageEntity {
        val events = mutableListOf(createPackageEvent(1L, id), createPackageEvent(2L, id));
        return PackageEntity(id, "2000-01-0"+id, "2000-01-0"+id, "CONFIRMED", events);
    }

    fun createPackageEvent(id:Long, packageId:Long): PackageEvent {
        return PackageEvent(id, "Type", "AAA"+id, "description"+id, "Aqui", "Aculá", packageId)
    }
}