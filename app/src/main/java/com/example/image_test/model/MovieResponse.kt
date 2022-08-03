package com.example.image_test.model

data class MovieResponse(
    val title: String,
    val image: String,
    val rating: Double,
    val releaseYear: Int,
    val genre: List<String>
)
