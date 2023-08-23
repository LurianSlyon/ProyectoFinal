package com.lurian.ecf_luna.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lurian.ecf_luna.modal.Emojis
import com.google.firebase.firestore.FirebaseFirestore


class EmojiListFirebaceViewModel : ViewModel() {
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()

    private val _emojis: MutableLiveData<List<Emojis>> = MutableLiveData()
    val emojis: LiveData<List<Emojis>> get() = _emojis

    fun fetchEmojis() {
        firestore.collection("Emoji")
            .get()
            .addOnSuccessListener { result ->
                val emojisList = mutableListOf<Emojis>()
                for (document in result) {
                    val name = document.getString("name") ?: ""
                    val category = document.getString("category") ?: ""
                    val group = document.getString("group") ?: ""
                    val htmlCode = document.getString("htmlCode") ?: ""
                    val unicode = document.getString("unicode") ?: ""
                    val simple_thumb = document.getString("simple_thumb") ?: ""
                    val isFavorite = document.getBoolean("isFavorite") ?: false

                    val emoji = Emojis(name, category, group, htmlCode, unicode, simple_thumb,isFavorite)
                    emojisList.add(emoji)
                }
                _emojis.value = emojisList
            }
            .addOnFailureListener { exception ->
                // Manejar el error, si es necesario
            }
    }
}
