package com.medium.rickandmorty

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.medium.rickandmorty.data.remote.model.CharacterResponseModel
import com.medium.rickandmorty.data.remote.service.CharacterService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service : CharacterService = retrofit.create(CharacterService::class.java)

        service.getAllCharacters().enqueue(object : Callback<CharacterResponseModel>{
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