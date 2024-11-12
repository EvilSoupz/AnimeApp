package com.example.isekaiapp.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Pagination (
    @SerialName("last_visible_page")
    val lastVisiblePage : Int,
    @SerialName("has_next_page")
    val hasNextPage : Boolean,
    @SerialName("current_page")
    val currentPage : Int,
    val items : PaginatingItems

    )

@Serializable
data class PaginatingItems(
    val count : Int,
    val total : Int,
    @SerialName("per_page")
    val perPage : Int
)