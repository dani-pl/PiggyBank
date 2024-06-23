package com.danipl.piggybank

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform