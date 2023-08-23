package com.lurian.ecf_luna.fragmentos
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.lurian.ec3_luna.databinding.FragmentListfirbaseBinding
import com.lurian.ecf_luna.AddEmojiActivity
import com.lurian.ecf_luna.modal.Emojis
import com.lurian.ecf_luna.viewmodel.EmojiListFirebaceViewModel

class ListFirebaseFragment : Fragment() {

    private lateinit var binding: FragmentListfirbaseBinding
    private lateinit var viewModel: EmojiListFirebaceViewModel
    private lateinit var adapter: EmojisFirebaseListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListfirbaseBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(EmojiListFirebaceViewModel::class.java)
        adapter = EmojisFirebaseListAdapter(emptyList()) { emoji ->
            val destino = ListFirebaseFragmentDirections.actionListfirebaseFragmentToDetailFragment(emoji) // Usa la acción de navegación correcta
            findNavController().navigate(destino)
        }

        // Configurar el LayoutManager (por ejemplo, LinearLayoutManager)
        binding.rvEmojisListf.layoutManager = LinearLayoutManager(requireContext())

        binding.rvEmojisListf.adapter = adapter

        viewModel.emojis.observe(viewLifecycleOwner) { emojis ->
            adapter.emojis = emojis
            adapter.notifyDataSetChanged()
        }

        viewModel.fetchEmojis()
        binding.btnAdd.setOnClickListener {
            val intent = Intent(requireContext(), AddEmojiActivity::class.java)
            startActivity(intent)
        }
    }

}