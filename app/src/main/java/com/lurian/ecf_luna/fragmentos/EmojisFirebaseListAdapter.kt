package com.lurian.ecf_luna.fragmentos
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lurian.ec3_luna.databinding.ItemsBinding
import com.lurian.ecf_luna.modal.Emojis

class EmojisFirebaseListAdapter(var emojis: List<Emojis>, val onNoteClick: (Emojis) -> Unit) : RecyclerView.Adapter<EmojisFirebaseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmojisFirebaseViewHolder {
        val binding = ItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EmojisFirebaseViewHolder(binding, onNoteClick)
    }

    override fun getItemCount(): Int = emojis.size

    override fun onBindViewHolder(holder: EmojisFirebaseViewHolder, position: Int) {
        holder.bind(emojis[position])
    }
}

class EmojisFirebaseViewHolder(
    private val binding: ItemsBinding,
    val onNoteClick: (Emojis) -> Unit
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(emoji: Emojis) {
        binding.txtName.text = "Nombre: ${emoji.name}"
        binding.txtCategory.text = "Categoría: ${emoji.category}"
        binding.txtGroup.text = "Grupo: ${emoji.group}"
        binding.txtHtmlCode.text = "HTML Code: ${emoji.htmlCode}"
        binding.txtUnicode.text = "Unicode: ${emoji.unicode}"
        Glide.with(binding.root.context).load(emoji.simple_thumb).into(binding.imgenw)
        binding.root.setOnClickListener { onNoteClick(emoji) }
    }
}