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
        val list = mutableListOf<CatFact>()
        val adapter = CatAdapter(list, this)
        val layout = LinearLayoutManager(requireContext())
        binding.recyclerView.layoutManager = layout
        binding.recyclerView.adapter = adapter



        binding.button.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val fact = RetrofitInstance.retrofit.fact()
                    list.add(fact)
                    withContext(Dispatchers.Main) {
                        adapter.notifyItemInserted(list.size - 1)
                        delay(500L)
                        try {
                            layout.scrollToPosition(list.size - 1)
                            delay(500L)
                            binding.scrollView.post { binding.scrollView.fullScroll(ScrollView.FOCUS_DOWN) }
                        } catch (e: Exception) {
                        }
                    }
                } catch (e: Exception) {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(
                            requireContext(),
                            "Нема підключення до Інтернету",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(data: String) {
        val bundle = Bundle()
        bundle.putString("fact", data)
        Navigation.findNavController(this.requireView()).navigate(R.id.detailsFragment, bundle)

    }

}

interface Listener {
    fun onClick(data: String)
}