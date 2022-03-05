package com.maad.touristapp

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface FlickerCallable {

    @GET("/services/rest?api_key=9a2e83f7864c06a9862d86e23585e527&method=flickr.photos.search&format=json&nojsoncallback=1&extras=url_m&page=1")
    fun getRandomPics(@Query("lat") lat: Double?, @Query("lon") lon: Double?):
            Call<RandomImageModel>

}