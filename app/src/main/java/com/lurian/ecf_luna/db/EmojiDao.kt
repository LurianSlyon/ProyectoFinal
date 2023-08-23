package com.lurian.ecf_luna.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.lurian.ecf_luna.modal.EmojiEntity

@Dao
interface EmojiDao {
    @Insert
    suspend fun addEmojisToFavorites(emoji:EmojiEntity)

    @Query("SELECT * FROM emojis")
    suspend fun getFavorite(): List<EmojiEntity>
}