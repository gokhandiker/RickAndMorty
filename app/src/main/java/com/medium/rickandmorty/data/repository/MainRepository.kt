package com.medium.rickandmorty.data.repository

import com.medium.rickandmorty.data.remote.helper.CharacterHelper
import com.medium.rickandmorty.data.remote.model.CharacterResponseModel
import io.reactivex.Observable
import retrofit2.Call
import javax.inject.Inject

class MainRepository @Inject constructor(private val characterHelper: CharacterHelper) {
    fun getAllCharacters(): Observable<CharacterResponseModel> {
        return characterHelper.getAllCharacters()
    }
}