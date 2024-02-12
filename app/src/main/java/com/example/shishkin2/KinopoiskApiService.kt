package com.example.shishkin2

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface KinopoiskApiService {
    @GET("/api/v2.2/films/{id}")
    //suspend fun getMovieDetails(@Path("id") id: Int): Response<MovieDetails>
    abstract fun getPopularFilms(s: String): Any
}