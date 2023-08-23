package com.lurian.ecf_luna.repository

import com.lurian.ecf_luna.EmojiServiceResult
import com.lurian.ecf_luna.db.EmojiDao
import com.lurian.ecf_luna.db.EmojiDataBase
import com.lurian.ecf_luna.modal.Emojis
import com.lurian.ecf_luna.modal.toEmoji
import com.lurian.ecf_luna.modal.toEmojiEntity
import com.lurian.ecf_luna.retrofit.RetrofitHelper
import java.lang.Exception

class EmojiRepository(db: EmojiDataBase? = null) {
    private val dao: EmojiDao? = db?.emojiDao()

    suspend fun getEmojis(): EmojiServiceResult<List<Emojis>> {
        return try {
            val response = RetrofitHelper.emojiService.getAllEmojis()
            EmojiServiceResult.Success(response)
        } catch (e: Exception) {
            EmojiServiceResult.Error(e)
        }
    }
    suspend fun getFavorites(): List<Emojis> {
        dao?.let {
            val data = dao.getFavorite()
            val emojis = mutableListOf<Emojis>()
            for (emojiEntity in data) {
                emojis.add(emojiEntity.toEmoji())
            }
            return emojis
        } ?: kotlin.run { return emptyList() }
    }
    suspend fun addEmojisToFavorites(emojis: Emojis){
        dao?.let {
            dao.addEmojisToFavorites(emojis.toEmojiEntity()) }
    }
}

