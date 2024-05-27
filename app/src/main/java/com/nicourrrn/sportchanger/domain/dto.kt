package com.nicourrrn.sportchanger.domain

import kotlinx.serialization.Serializable

@Serializable
data class Counter(val count: Int)

@Serializable
data class Task (
    val name: String,
    val done: Boolean
)
