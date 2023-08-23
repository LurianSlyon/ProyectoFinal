package com.lurian.ecf_luna.fragmentos

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lurian.ec3_luna.databinding.ItemsBinding
import com.lurian.ecf_luna.modal.Emojis

// adapter/EmojisListAdapter.kt
class EmojisListAdapter(var emojis: List<Emojis>, val onNoteClick:(Emojis)->Unit) : RecyclerView.Adapter<EmojisViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmojisViewHolder {
        val binding = ItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EmojisViewHolder(binding,onNoteClick)
    }

    override fun getItemCount(): Int = emojis.size

    override fun onBindViewHolder(holder: EmojisViewHolder, position: Int) {
        holder.bind(emojis[position])
    }
}

class EmojisViewHolder(private val binding: ItemsBinding, val onEmojiClick:(Emojis)->Unit) : RecyclerView.ViewHolder(binding.root) {
    fun bind(emoji: Emojis) {
        binding.txtName.text =  "Nombre: ${emoji.name}"
        binding.txtCategory.text = "Category: ${emoji.category}"
        binding.txtGroup.text = "Group: ${emoji.group}"
        binding.txtHtmlCode.text = "HTML Code: ${emoji.htmlCode}"
        binding.txtUnicode.text = "Unicode: ${emoji.unicode}"
        Glide.with(binding.root.context).load(emoji.simple_thumb).into(binding.imgenw)
        binding.root.setOnClickListener{ onEmojiClick(emoji)}
    }
}
