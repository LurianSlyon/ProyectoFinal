package com.lurian.ecf_luna.modal

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "emojis")
data class EmojiEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val category: String,
    val group: String,
    val htmlCode: String,
    val unicode: String,
    val simple_thumb: String,
    var isFavorite: Boolean = false
)
fun EmojiEntity.toEmoji():Emojis{
    return Emojis(
        name, category, group, htmlCode, unicode,simple_thumb, isFavorite
    )
}
