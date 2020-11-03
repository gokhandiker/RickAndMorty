package com.medium.rickandmorty.data.remote.helper

import com.medium.rickandmorty.data.remote.model.CharacterResponseModel
import com.medium.rickandmorty.data.remote.service.CharacterService
import io.reactivex.Observable
import retrofit2.Call


class CharacterHelper (private val characterService: CharacterService) {

    fun getAllCharacters(): Observable<CharacterResponseModel> {
        return characterService.getAllCharacters()
    }

}