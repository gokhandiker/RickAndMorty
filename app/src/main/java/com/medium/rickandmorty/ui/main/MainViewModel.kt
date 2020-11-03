package com.medium.rickandmorty.ui.main

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.medium.rickandmorty.data.remote.helper.CharacterHelper
import com.medium.rickandmorty.data.remote.model.CharacterResponseModel
import com.medium.rickandmorty.data.remote.service.CharacterService
import com.medium.rickandmorty.data.repository.MainRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MainViewModel : ViewModel() {

    private val _characters : MutableLiveData<CharacterResponseModel> = MutableLiveData()
    val characters : LiveData<CharacterResponseModel> = _characters


    private var mainRepository: MainRepository
    private var characterHelper: CharacterHelper

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // burası eklendi
            .build()

        val service: CharacterService = retrofit.create(CharacterService::class.java)

        characterHelper = CharacterHelper(service)
        mainRepository = MainRepository(characterHelper)
    }


    @SuppressLint("CheckResult")
    fun getAllCharacters() {
        mainRepository.getAllCharacters()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                    result -> _characters.value = result
            },{
                    error -> println(error.message.toString())
            })
    }
}