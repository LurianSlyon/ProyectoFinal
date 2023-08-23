package com.lurian.ecf_luna.fragmentos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.lurian.ec3_luna.databinding.FragmentFavoriteBinding
import com.lurian.ecf_luna.db.EmojiDataBase
import com.lurian.ecf_luna.viewmodel.EmojiFavoriteViewModel

class FavoriteFragment : Fragment() {

    private lateinit var binding: FragmentFavoriteBinding
    private lateinit var viewModel: EmojiFavoriteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inicializar el viewModel
        viewModel = ViewModelProvider(this).get(EmojiFavoriteViewModel::class.java)

        val adapter = EmojisListAdapter(listOf()) { emoji ->
            val destino = FavoriteFragmentDirections.actionFavoriteFragmentToDetailFragment(emoji)
            findNavController().navigate(destino)
        }
        binding.rvFavotite.adapter = adapter
        viewModel.favorites.observe(viewLifecycleOwner) { favorites ->
            adapter.emojis = favorites
            adapter.notifyDataSetChanged()
        }
        viewModel.getFavorite()
    }
}
