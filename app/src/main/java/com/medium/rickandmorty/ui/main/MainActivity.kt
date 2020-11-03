package com.medium.rickandmorty.ui.main

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.medium.rickandmorty.R
import com.medium.rickandmorty.data.remote.helper.CharacterHelper
import com.medium.rickandmorty.data.remote.model.CharacterResponseModel
import com.medium.rickandmorty.data.remote.service.CharacterService
import com.medium.rickandmorty.data.repository.MainRepository
import com.medium.rickandmorty.di.component.DaggerAppComponent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var mainViewModel: MainViewModel

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerAppComponent.create().inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       // mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        mainViewModel.characters.observe(this, {
            println(it.toString())
        })

        mainViewModel.getAllCharacters()
    }
}