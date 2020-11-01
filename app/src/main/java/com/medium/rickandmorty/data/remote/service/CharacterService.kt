package com.medium.rickandmorty.data.remote.service

import com.medium.rickandmorty.data.remote.model.CharacterResponseModel
import retrofit2.Call
import retrofit2.http.GET

interface CharacterService {

    @GET("character/")
    fun getAllCharacters(): Call<CharacterResponseModel>

}