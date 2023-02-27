package com.example.newapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ScrollView
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newapp.databinding.FragmentMainBinding
import kotlinx.coroutines.*

class MainFragment : Fragment(), Listener {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val list = mutableListOf<CatFact>(
            CatFact("Cats can't be evil", "Fact1", CatUrl.URL1.url),
            CatFact("Cats are cute", "Fact2", CatUrl.URL2.url),
            CatFact("Feed your cat, pls", "Fact3", CatUrl.URL3.url),
            CatFact("I'm a cat","Fact4", CatUrl.URL4.url),
        )
        val adapter = CatAdapter(list, this)
        val layout = LinearLayoutManager(requireContext())
        binding.recyclerView.layoutManager = layout
        binding.recyclerView.adapter = adapter

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(catFact: CatFact) {
        val bundle = Bundle()
        bundle.putString("fact", catFact.fact)
        bundle.putString("url", catFact.url)
        Navigation.findNavController(this.requireView()).navigate(R.id.detailsFragment, bundle)

    }

}

interface Listener {
    fun onClick(catFact: CatFact)
}