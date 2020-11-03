package com.medium.rickandmorty.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.medium.rickandmorty.R
import com.medium.rickandmorty.data.remote.helper.CharacterHelper
import com.medium.rickandmorty.data.remote.model.CharacterResponseModel
import com.medium.rickandmorty.data.remote.service.CharacterService
import com.medium.rickandmorty.data.repository.MainRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    lateinit var mainRepository: MainRepository
    lateinit var characterHelper: CharacterHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service : CharacterService = retrofit.create(CharacterService::class.java)


        characterHelper  = CharacterHelper(service)
        mainRepository = MainRepository(characterHelper)

        mainRepository.getAllCharacters().enqueue(object : Callback<CharacterResponseModel>{
            override fun onResponse(
                    call: Call<CharacterResponseModel>,
                    response: Response<CharacterResponseModel>
            ) {
                println(response.body())
            }

            override fun onFailure(call: Call<CharacterResponseModel>, t: Throwable) {
                println(t.message)
            }

        })

    }
}