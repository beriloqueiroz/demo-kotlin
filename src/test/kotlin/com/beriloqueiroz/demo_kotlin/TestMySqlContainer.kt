package com.beriloqueiroz.demo_kotlin

import org.testcontainers.containers.MySQLContainer

object TestMySqlContainer : MySQLContainer<TestMySqlContainer>("mysql:5.7") {

    override fun start() {
        super.start()
        System.setProperty("DB_URL", this.jdbcUrl)
        System.setProperty("DB_USERNAME", this.username)
        System.setProperty("DB_PASSWORD", this.password)
    }

    override fun stop() {
        //do nothing jvm handles it
    }

    override fun getDriverClassName(): String = "com.mysql.cj.jdbc.Driver"

}