package com.lurian.ecf_luna.retrofit

import com.lurian.ecf_luna.modal.Emojis
import retrofit2.http.GET

interface EmojiService {
    @GET("?format=json")
    suspend fun getAllEmojis(): List<Emojis>
}
