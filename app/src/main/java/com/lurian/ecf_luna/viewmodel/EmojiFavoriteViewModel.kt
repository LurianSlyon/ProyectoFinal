package com.lurian.ecf_luna.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.lurian.ecf_luna.db.EmojiDataBase
import com.lurian.ecf_luna.modal.Emojis
import com.lurian.ecf_luna.repository.EmojiRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class EmojiFavoriteViewModel(application: Application): AndroidViewModel(application) {
    private val repository: EmojiRepository
    private var _favorite: MutableLiveData<List<Emojis>> = MutableLiveData()
    var favorites: LiveData<List<Emojis>> = _favorite
    init {
        val db = EmojiDataBase.getDatabase(application)
        repository = EmojiRepository(db) // Inicializa el repositorio con la base de datos
    }
    fun getFavorite() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val data = repository.getFavorites() // Asumiendo que el nombre del método es getFavorites()
                _favorite.postValue(data) // Usar postValue ya que estamos en un hilo de fondo
            } catch (e: Exception) {
                // Manejar errores, si es necesario
            }
        }
    }
}