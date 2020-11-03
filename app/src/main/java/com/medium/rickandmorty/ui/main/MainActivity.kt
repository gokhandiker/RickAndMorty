package com.medium.rickandmorty.ui.main

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.medium.rickandmorty.R
import com.medium.rickandmorty.data.remote.helper.CharacterHelper
import com.medium.rickandmorty.data.remote.model.CharacterResponseModel
import com.medium.rickandmorty.data.remote.service.CharacterService
import com.medium.rickandmorty.data.repository.MainRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    lateinit var mainRepository: MainRepository
    lateinit var characterHelper: CharacterHelper

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit = Retrofit.Builder()
                .baseUrl("https://rickandmortyapi.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // burası eklendi
                .build()

        val service: CharacterService = retrofit.create(CharacterService::class.java)


        characterHelper = CharacterHelper(service)
        mainRepository = MainRepository(characterHelper)

        mainRepository.getAllCharacters()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    result -> println(result.toString())
                },{
                    error -> println(error.message.toString())
                })


    }
}