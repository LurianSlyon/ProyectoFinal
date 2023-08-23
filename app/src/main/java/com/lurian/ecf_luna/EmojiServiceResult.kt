package com.lurian.ecf_luna

import com.lurian.ecf_luna.modal.Emojis
import java.lang.Exception

sealed class EmojiServiceResult<T>(data: T? = null, error: Exception? = null) {
    data class Success<T>(val data: T) : EmojiServiceResult<T>(data, null)
    data class Error<T>(val error: Exception) : EmojiServiceResult<T>(null, error)
}
