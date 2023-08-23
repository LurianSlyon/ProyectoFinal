package com.lurian.ecf_luna.fragmentos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.lurian.ec3_luna.R
import com.lurian.ec3_luna.databinding.FragmentDetailBinding
import com.lurian.ecf_luna.modal.Emojis
import com.lurian.ecf_luna.viewmodel.EmojiDetailViewModel

class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding
    private val args: DetailFragmentArgs by navArgs()
    private lateinit var emojis: Emojis
    private lateinit var viewModel: EmojiDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        emojis = args.emoji
        viewModel = ViewModelProvider(this).get(EmojiDetailViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.txtName.text = emojis.name
        binding.txtCategory.text = emojis.category
        binding.txtGroup.text = emojis.group
        binding.txtHtmlCode.text = emojis.htmlCode
        binding.txtUnicode.text = emojis.unicode
        binding.txtImage.text = emojis.simple_thumb
        Glide.with(binding.root.context).load(emojis.simple_thumb).into(binding.imgenw)

        if (emojis.isFavorite) {
            binding.btnAddFavorite.visibility = View.GONE
        }

        binding.btnAddFavorite.setOnClickListener {
            emojis.isFavorite = true
            viewModel.addFavorite(emojis)
            Snackbar.make(binding.root, "Agregado a favoritos", Snackbar.LENGTH_SHORT).show()
        }
    }
}