package com.lurian.ecf_luna.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.lurian.ecf_luna.db.EmojiDataBase
import com.lurian.ecf_luna.modal.Emojis
import com.lurian.ecf_luna.repository.EmojiRepository

class EmojiDetailViewModel(application: Application): AndroidViewModel(application) {
    private val repository: EmojiRepository
    init {
        val db = EmojiDataBase.getDatabase(application)
        repository= EmojiRepository(db)
    }
    fun addFavorite(emojis:Emojis){
        viewModelScope.launch(Dispatchers.IO){
            repository.addEmojisToFavorites(emojis)
        }
    }
}