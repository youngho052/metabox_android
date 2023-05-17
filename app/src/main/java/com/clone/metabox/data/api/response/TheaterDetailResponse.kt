package com.clone.metabox.data.api.response

import androidx.annotation.Keep

//@Keep
//data class TheaterDetailResponse(val _id: String = "") {
//    lateinit var name: String
//    lateinit var description: String
//    lateinit var subscription: String
//    lateinit var facilities: List<String>
//    lateinit var floorInformation: List<FloorInformation>
//    lateinit var state: String
//    lateinit var address: String
//    lateinit var createdAt: String
//    lateinit var updatedAt: String
//}

@Keep
data class TheaterDetailResponse (
    val _id: String = "",
    val name: String = "",
    val description: String = "",
    val subscription: String = "",
    val facilities: List<String> = listOf(),
    val floorInformation: List<FloorInformation> = listOf(FloorInformation()),
    val state: String = "",
    val address: String = "",
    val createdAt: String = "",
    val updatedAt: String = "",
)

@Keep
data class FloorInformation (
    val floor: String = "",
    val information: List<String> = listOf()
)
