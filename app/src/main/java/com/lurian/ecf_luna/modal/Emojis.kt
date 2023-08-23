package com.lurian.ecf_luna.modal

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
@Parcelize
data class Emojis(
    @SerializedName("title")
    val name: String,
    @SerializedName("epoch")
    val category: String ,
    @SerializedName("author")
    val group: String,
    @SerializedName("genre")
    val htmlCode: String,
    @SerializedName("slug")
    val unicode: String,
    val simple_thumb: String,
    var isFavorite: Boolean = false
): Parcelable
fun Emojis.toEmojiEntity():EmojiEntity{
    return EmojiEntity(
        0,name, category, group, htmlCode, unicode, simple_thumb,isFavorite
    )
}

