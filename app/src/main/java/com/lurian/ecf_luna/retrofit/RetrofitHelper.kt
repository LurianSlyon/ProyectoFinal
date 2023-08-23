package com.lurian.ecf_luna.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://wolnelektury.pl/api/audiobooks/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val emojiService: EmojiService = retrofit.create(EmojiService::class.java)
}
