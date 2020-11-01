package com.medium.rickandmorty.data.remote.model

data class CharacterResponseModel(
    val info: Info,
    val results: List<CharacterModel>
)

data class Info(
    val count: Int,
    val next: String,
    val pages: Int,
    val prev: String
)