package com.lurian.ecf_luna

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.lurian.ec3_luna.R
import com.lurian.ec3_luna.databinding.ActivityAddEmojiBinding

class AddEmojiActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddEmojiBinding
    private lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddEmojiBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firestore = Firebase.firestore
        binding.btnRegister.setOnClickListener {
            val name = binding.tilName.editText?.text.toString()
            val category = binding.tilCategory.editText?.text.toString()
            val group= binding.tilGroup.editText?.text.toString()
            val htmlCode = binding.tilHtmlcode.editText?.text.toString()
            val unicode = binding.tilUnicodecode.editText?.text.toString()
            val favorite = binding.switchFavorite.isChecked
            if (name.isNotEmpty() && category.isNotEmpty() && group.isNotEmpty()&& htmlCode.isNotEmpty()&& unicode.isNotEmpty()){
                addToFirestore(name, category, group,htmlCode,unicode, favorite)
            }
        }
    }

    private fun addToFirestore(name: String, category: String, group: String, htmlCode: String, unicode: String, favorite: Boolean) {
        val newEmoji = hashMapOf<String, Any>(
            "name" to name,
            "category" to category,
            "group" to group,
            "isFavorite" to favorite,
            "htmlCode" to htmlCode,
            "unicode" to unicode,
        )
        firestore.collection("Emoji").add(newEmoji)
            .addOnSuccessListener {
                Toast.makeText(this, "Emoji agregado con id ${it.id}", Toast.LENGTH_SHORT).show()
            }
            .addOnSuccessListener {
                Toast.makeText(this, "Ocurrio un error", Toast.LENGTH_SHORT).show() }
    }
}