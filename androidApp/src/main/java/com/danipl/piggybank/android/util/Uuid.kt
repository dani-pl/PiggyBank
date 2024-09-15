package com.danipl.piggybank.android.util

import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
fun Uuid.toLong(): Long = toLongs { mostSignificantBits, leastSignificantBits ->
    mostSignificantBits
}
