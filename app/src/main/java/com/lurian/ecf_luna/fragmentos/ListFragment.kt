package com.lurian.ecf_luna.fragmentos


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.lurian.ec3_luna.databinding.FragmentListBinding
import com.lurian.ecf_luna.viewmodel.EmojiListViewModel

class ListFragment : Fragment() {

    private lateinit var binding: FragmentListBinding
    private lateinit var adapter: EmojisListAdapter
    private lateinit var viewModel: EmojiListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = EmojisListAdapter(listOf())  {emoji->
            val destino = ListFragmentDirections.actionListFragmentToDetailFragment(emoji)
            findNavController().navigate(destino)
        }
        binding.rvEmojisList.adapter = adapter
        binding.rvEmojisList.layoutManager = LinearLayoutManager(requireContext())

        viewModel = ViewModelProvider(this).get(EmojiListViewModel::class.java)

        viewModel.emojis.observe(viewLifecycleOwner, Observer { emojis ->
            adapter.emojis = emojis
            adapter.notifyDataSetChanged()
        })

        viewModel.getAllEmojis()
    }
}